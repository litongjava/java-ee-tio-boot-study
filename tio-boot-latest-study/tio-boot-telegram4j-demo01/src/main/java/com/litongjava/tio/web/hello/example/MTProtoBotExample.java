package com.litongjava.tio.web.hello.example;

import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.reactivestreams.Publisher;

import com.litongjava.telegram.command.Command;
import com.litongjava.telegram.command.EchoCommand;
import com.litongjava.telegram.command.PingCommand;
import com.litongjava.telegram.command.ShrugCommand;
import com.litongjava.tio.utils.environment.EnvUtils;

import io.netty.util.ResourceLeakDetector;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;
import telegram4j.core.MTProtoBootstrap;
import telegram4j.core.MTProtoTelegramClient;
import telegram4j.core.event.domain.message.SendMessageEvent;
import telegram4j.core.object.MessageEntity;
import telegram4j.core.retriever.EntityRetrievalStrategy;
import telegram4j.core.retriever.PreferredEntityRetriever.Setting;
import telegram4j.core.spec.BotCommandScopeSpec;
import telegram4j.core.spec.BotCommandScopeSpec.Type;
import telegram4j.mtproto.MTProtoRetrySpec;
import telegram4j.mtproto.MethodPredicate;
import telegram4j.mtproto.ResponseTransformer;
import telegram4j.mtproto.store.FileStoreLayout;
import telegram4j.mtproto.store.StoreLayoutImpl;

public class MTProtoBotExample {

  private static final List<Command> commands = List.of(new EchoCommand(), new ShrugCommand(), new PingCommand());
  private static final Map<String, Command> commandsMap = commands.stream().collect(
      //
      Collectors.toMap(c -> c.getInfo().command().toLowerCase(Locale.ROOT), Function.identity()));

  public static void main(String[] args) {
    EnvUtils.load();

    // only for testing, do not copy it to your production code!!!
    Hooks.onOperatorDebug();
    ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.PARANOID);

    int apiId = EnvUtils.getInt("telegram.api.id");
    String apiHash = EnvUtils.getStr("telegram.api.hash");
    String botAuthToken = EnvUtils.getStr("telegram.bot.auth.token");

    MTProtoBootstrap bootstrap = MTProtoTelegramClient.create(apiId, apiHash, botAuthToken);

    // prefer retrieving full data about peer entities
    EntityRetrievalStrategy preferred = EntityRetrievalStrategy.preferred(
        //
        EntityRetrievalStrategy.STORE_FALLBACK_RPC, Setting.FULL, Setting.FULL);

    FileStoreLayout storeLayout = new FileStoreLayout(new StoreLayoutImpl(Function.identity()), Path.of("MTProtoBotExample.bin"));

    MTProtoRetrySpec max = MTProtoRetrySpec.max(d -> d.getSeconds() < 30, 2);

    ResponseTransformer responseTransformer = ResponseTransformer.retryFloodWait(MethodPredicate.all(), max);
    Function<MTProtoTelegramClient, ? extends Publisher<?>> func = client -> {

      Mono<Void> updateCommands = client.getCommands(BotCommandScopeSpec.of(Type.CHATS), "en")
          //
          .flatMap(list -> {
            var infos = commands.stream().map(Command::getInfo).collect(Collectors.toUnmodifiableList());

            if (list.equals(infos)) {
              return Mono.empty();
            }
            return client.setCommands(BotCommandScopeSpec.of(Type.CHATS), "en", infos);
          }).then();

      // If your bot doesn't respond to any message in the group,
      // then try to disable a privacy mode in the BotFather's settings:
      // /setprivacy -> <choose your bot> -> Disable
      Function<? super SendMessageEvent, ? extends Publisher<? extends Void>> mapper = e -> {
        List<MessageEntity> entities = e.getMessage().getEntities();
        return Mono.just(entities)
            //
            .filter(list -> !list.isEmpty() && list.get(0).getType() == MessageEntity.Type.BOT_COMMAND)
            //
            .map(list -> list.get(0)).flatMap(ent -> {
              String str = ent.getContent();
              int et = str.indexOf('@');
              String command = str.substring(str.indexOf('/') + 1, et != -1 ? et : str.length()).toLowerCase(Locale.ROOT);

              Mono<Command> monoCommand = Mono.fromSupplier(() -> commandsMap.get(command));
              Mono<Void> monoVoid = monoCommand.flatMap(c -> Mono.from(c.execute(e))).then();
              return monoVoid;
            });
      };

      Mono<Void> listenMessages = client.on(SendMessageEvent.class)
          //
          .flatMap(mapper).then();

      return Mono.when(updateCommands, listenMessages);
    };

    bootstrap.setEntityRetrieverStrategy(preferred)
        //
        .setStoreLayout(storeLayout)
        //
        .addResponseTransformer(responseTransformer)
        //
        .withConnection(func).block();
  }
}