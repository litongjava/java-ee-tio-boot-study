package com.litongjava.tio.web.hello.example;

import java.nio.file.Path;
import java.time.Duration;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.reactivestreams.Publisher;

import com.litongjava.telegram.command.Command;
import com.litongjava.telegram.command.EchoCommand;
import com.litongjava.telegram.command.PingCommand;
import com.litongjava.telegram.command.TelegramCommand;
import com.litongjava.tio.utils.environment.EnvUtils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.util.ResourceLeakDetector;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;
import telegram4j.core.MTProtoBootstrap;
import telegram4j.core.MTProtoTelegramClient;
import telegram4j.core.event.EventAdapter;
import telegram4j.core.event.domain.inline.CallbackQueryEvent;
import telegram4j.core.event.domain.inline.InlineCallbackQueryEvent;
import telegram4j.core.event.domain.inline.InlineQueryEvent;
import telegram4j.core.event.domain.message.SendMessageEvent;
import telegram4j.core.object.Message;
import telegram4j.core.object.MessageEntity;
import telegram4j.core.object.chat.Chat;
import telegram4j.core.object.markup.ReplyMarkup;
import telegram4j.core.spec.AnswerInlineCallbackQuerySpec;
import telegram4j.core.spec.BotCommandScopeSpec;
import telegram4j.core.spec.EditMessageSpec;
import telegram4j.core.spec.ReplyToMessageSpec;
import telegram4j.core.spec.SendMessageSpec;
import telegram4j.core.spec.inline.InlineMessageSpec;
import telegram4j.core.spec.inline.InlineResultArticleSpec;
import telegram4j.core.spec.markup.InlineButtonSpec;
import telegram4j.core.spec.markup.ReplyButtonSpec;
import telegram4j.core.spec.markup.ReplyMarkupSpec;
import telegram4j.core.util.Id;
import telegram4j.core.util.parser.EntityParserFactory;
import telegram4j.mtproto.store.FileStoreLayout;
import telegram4j.mtproto.store.StoreLayoutImpl;
import telegram4j.tl.BotCommand;

@Slf4j
public class BotButtonExample {
  private static final List<Command> commands = List.of(new BeginInlineCommand(),
      //
      new BeginReplyCommand(), new PingCommand(), new EchoCommand());
  private static final Map<String, Command> commandsMap = commands.stream()
      //
      .collect(Collectors.toUnmodifiableMap(
          //
          c -> c.getInfo().command(), Function.identity()));

  public static void main(String[] args) {
    EnvUtils.load();
    // only for testing, do not copy it to your production code!!!
    Hooks.onOperatorDebug();
    ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.PARANOID);

    int apiId = EnvUtils.getInt("telegram.api.id");
    String apiHash = EnvUtils.getStr("telegram.api.hash");
    String botAuthToken = EnvUtils.getStr("telegram.bot.auth.token");

    StoreLayoutImpl storeLayoutImpl = new StoreLayoutImpl(Function.identity());
    FileStoreLayout storeLayout = new FileStoreLayout(storeLayoutImpl, Path.of("t4j-bot.bin"));

    MTProtoBootstrap bootstrap = MTProtoTelegramClient.create(apiId, apiHash, botAuthToken);
    MTProtoTelegramClient client = bootstrap.setDefaultEntityParserFactory(EntityParserFactory.MARKDOWN_V2)
        //
        .setStoreLayout(storeLayout).connect().block();

    Objects.requireNonNull(client);

    BotCommandScopeSpec spec = BotCommandScopeSpec.of(BotCommandScopeSpec.Type.DEFAULT);

    List<BotCommand> list = commands.stream().map(Command::getInfo).toList();

    client.setCommands(spec, "en", list).subscribe();

    client.on(new BotEventAdapter()).subscribe();

    client.onDisconnect().block();
  }

  @TelegramCommand(command = "begin_inline", description = "Begin inline button demonstration")
  static class BeginInlineCommand implements Command {
    @Override
    public Publisher<?> execute(SendMessageEvent event) {
      Mono<Chat> monoChat = Mono.justOrEmpty(event.getChat()).switchIfEmpty(event.getMessage().getChat());

      Mono<Message> flatMap = monoChat.flatMap(chat -> {
        InlineButtonSpec callback = InlineButtonSpec.callback("Callback button", false,
            //
            Unpooled.copyInt(ThreadLocalRandom.current().nextInt()));

        Id id = event.getMessage().getAuthorId().orElseGet(event.getClient()::getSelfId);

        InlineButtonSpec userProfile = InlineButtonSpec.userProfile("User profile redirect button", id);

        InlineButtonSpec url = InlineButtonSpec.url("Url button", "https://www.google.com/");
        InlineButtonSpec switchInline = InlineButtonSpec.switchInline("Switch to inline query", true, "1234");

        List<List<InlineButtonSpec>> rows = List.of(List.of(callback, userProfile), List.of(url, switchInline));

        ReplyMarkupSpec inlineKeyboard = ReplyMarkupSpec.inlineKeyboard(rows);

        return chat.sendMessage(
            //
            SendMessageSpec.of("Please select an inline button!")
                //
                .withReplyTo(ReplyToMessageSpec.of(event.getMessage()))
                //
                .withReplyMarkup(inlineKeyboard));
      });
      return flatMap;
    }
  }

  @TelegramCommand(command = "begin_reply", description = "Begin reply button demonstration")
  static class BeginReplyCommand implements Command {
    static final ReplyMarkupSpec groupsMarkup = ReplyMarkupSpec.keyboard(null,
        //
        List.of(List.of(ReplyButtonSpec.text("Just text button"), ReplyButtonSpec.text("Another text button"))),
        //
        EnumSet.allOf(ReplyMarkup.Flag.class));

    static final ReplyMarkupSpec dmMarkup = ReplyMarkupSpec.keyboard(null,
        //
        List.of(List.of(ReplyButtonSpec.requestPoll("Request an quiz poll", true),
            //
            ReplyButtonSpec.requestPoll("Request a regular poll", false),
            //
            ReplyButtonSpec.requestPoll("Request any poll", null))),
        //
        EnumSet.allOf(ReplyMarkup.Flag.class));

    @Override
    public Publisher<?> execute(SendMessageEvent event) {
      Mono<Chat> monoChat = Mono.justOrEmpty(event.getChat()).switchIfEmpty(event.getMessage().getChat());

      Function<? super Chat, ? extends Mono<? extends Message>> transformer = chat -> {
        Message message = event.getMessage();
        ReplyToMessageSpec replyMessage = ReplyToMessageSpec.of(message);
        String value = "Please select a reply button! " + "_Note: I have different keyboards in groups and private chats_";
        ReplyMarkupSpec replyMarkupSpec = chat.getType() == Chat.Type.PRIVATE ? dmMarkup : groupsMarkup;
        SendMessageSpec withReplyTo = SendMessageSpec.of(value).withReplyMarkup(replyMarkupSpec).withReplyTo(replyMessage);

        return chat.sendMessage(withReplyTo);
      };

      return monoChat.flatMap(transformer);
    }
  }

  static class BotEventAdapter extends EventAdapter {
    @Override
    public Publisher<?> onInlineCallbackQuery(InlineCallbackQueryEvent event) {
      String hexDump = event.getData().map(ByteBufUtil::hexDump).orElseThrow();
      String value = "**Inline callback data:** " + hexDump;

      EditMessageSpec spec = EditMessageSpec.of().withMessage(value);
      return event.edit(spec);
    }

    @Override
    public Publisher<?> onInlineQuery(InlineQueryEvent event) {
      long queryId = event.getQueryId();
      log.info("queryId:{}", queryId);
      ByteBuf byteBuf = Unpooled.copyLong(queryId);
      InlineButtonSpec callback = InlineButtonSpec.callback("Inline callback button", false, byteBuf);

      List<InlineButtonSpec> buttonSpec = List.of(callback);
      List<List<InlineButtonSpec>> buttonSpecList = List.of(buttonSpec);

      ReplyMarkupSpec inlineKeyboard = ReplyMarkupSpec.inlineKeyboard(buttonSpecList);

      InlineMessageSpec text = InlineMessageSpec.text("Link to site: https://core.telegram.org/schema");
      InlineMessageSpec withReplyMarkup = text.withReplyMarkup(inlineKeyboard);

      InlineResultArticleSpec articleSpec = InlineResultArticleSpec.of(
          //
          "The most updated and never abandoned site!", "https://core.telegram.org/schema", "one",
          //
          withReplyMarkup);

      List<InlineResultArticleSpec> results = List.of(articleSpec);

      AnswerInlineCallbackQuerySpec spec = AnswerInlineCallbackQuerySpec.of(Duration.ZERO, results);

      return event.answer(spec);
    }

    @Override
    public Publisher<?> onCallbackQuery(CallbackQueryEvent event) {
      String text = "**Callback data:** " + event.getData().map(ByteBufUtil::hexDump).orElseThrow();
      return event.getChat().sendMessage(text);
    }

    @Override
    public Publisher<?> onSendMessage(SendMessageEvent event) {
      String message = event.getMessage().getContent();
      log.info("message:{}", message);

      Predicate<? super MessageEntity> predicate = p -> p.getType() == MessageEntity.Type.BOT_COMMAND &&
      //
          p.getContent().equals(message);

      List<MessageEntity> entities = event.getMessage().getEntities();

      Optional<? extends String> first = entities.stream().filter(predicate)
          //
          .map(MessageEntity::getContent).findFirst();
      log.info("first:{}", first.get());
      Function<? super String, ? extends Command> mapper2 = commandsMap::get;

      Mono<? extends String> monoString = Mono.justOrEmpty(first);
      Mono<?> publisher = monoString
          //
          .map(s -> s.substring(1)).mapNotNull(mapper2)
          //
          .flatMap(s -> {
            Publisher<?> execute = s.execute(event);
            return Mono.from(execute);
          });
      return publisher;
    }
  }

}