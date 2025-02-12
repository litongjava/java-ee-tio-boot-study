package com.litongjava.tio.web.hello.example;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.reactivestreams.Publisher;

import com.litongjava.telegram.command.Command;
import com.litongjava.telegram.command.TelegramCommand;
import com.litongjava.tio.utils.environment.EnvUtils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.ResourceLeakDetector;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;
import telegram4j.core.MTProtoBootstrap;
import telegram4j.core.MTProtoTelegramClient;
import telegram4j.core.event.EventAdapter;
import telegram4j.core.event.domain.inline.CallbackQueryEvent;
import telegram4j.core.event.domain.message.SendMessageEvent;
import telegram4j.core.object.MessageEntity;
import telegram4j.core.object.chat.Chat;
import telegram4j.core.spec.BotCommandScopeSpec;
import telegram4j.core.spec.SendMessageSpec;
import telegram4j.core.spec.markup.InlineButtonSpec;
import telegram4j.core.spec.markup.ReplyMarkupSpec;
import telegram4j.core.util.parser.EntityParserFactory;
import telegram4j.mtproto.store.FileStoreLayout;
import telegram4j.mtproto.store.StoreLayoutImpl;
import telegram4j.tl.BotCommand;

@Slf4j
public class BotMenuExample {

  private static final List<Command> commands = List.of(new StartCommand());
  private static final Map<String, Command> commandsMap = commands.stream()
      //
      .collect(Collectors.toUnmodifiableMap(c -> c.getInfo().command(), Function.identity()));

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

    // Attach the event adapter
    client.on(new BotEventAdapter()).subscribe();
    client.onDisconnect().block();
  }

  // /start command to display the main menu
  @TelegramCommand(command = "start", description = "Start and display the main menu")
  static class StartCommand implements Command {
    @Override
    public Publisher<?> execute(SendMessageEvent event) {
      Mono<Chat> monoChat = Mono.justOrEmpty(event.getChat()).switchIfEmpty(event.getMessage().getChat());
      return monoChat.flatMap(chat -> {
        List<List<InlineButtonSpec>> rows = List.of(
            //
            List.of(InlineButtonSpec.callback("申请加入车队", false, Unpooled.wrappedBuffer("apply".getBytes()))),
            List.of(InlineButtonSpec.callback("查看车队详情", false, Unpooled.wrappedBuffer("view_teams".getBytes()))));

        ReplyMarkupSpec markup = ReplyMarkupSpec.inlineKeyboard(rows);
        SendMessageSpec messageSpec = SendMessageSpec.of("🚗 欢迎使用车队管理系统，请选择操作：").withReplyMarkup(markup);
        return chat.sendMessage(messageSpec);
      });
    }
  }

  // Handle callback queries
  public Publisher<?> onCallbackQuery(CallbackQueryEvent event) {
    Optional<ByteBuf> byteBuf = event.getData();
    String data = byteBuf.map(buf -> buf.toString(StandardCharsets.UTF_8)).orElse("");
    switch (data) {
    case "apply":
      return handleApply(event);
    case "view_teams":
      return showTeams(event, 1); // Default to page 1
    default:
      if (data.startsWith("view_team_")) {
        String teamId = data.substring("view_team_".length());
        return showTeamDetails(event, teamId);
      } else if (data.startsWith("page_")) {
        int page = Integer.parseInt(data.substring("page_".length()));
        return showTeams(event, page);
      }
      return Mono.empty();
    }
  }

  // Handle team application
  private Publisher<?> handleApply(CallbackQueryEvent event) {
    SendMessageSpec spec = SendMessageSpec.of("🚗 申请加入车队请填写相关信息：\n- 车队名\n- 联系方式");
    return event.getChat().sendMessage(spec);
  }

  // Show paginated team list (two-column layout)
  private Publisher<?> showTeams(CallbackQueryEvent event, int page) {
    List<String> teams = IntStream.range(1, 51).mapToObj(i -> "车队 " + i).collect(Collectors.toList());
    int itemsPerPage = 10;
    int totalPages = (int) Math.ceil(teams.size() / (double) itemsPerPage);

    int start = (page - 1) * itemsPerPage;
    int end = Math.min(start + itemsPerPage, teams.size());
    List<String> pageItems = teams.subList(start, end);

    // Arrange teams in two columns
    IntStream ints = IntStream.range(0, pageItems.size());
    Map<Integer, List<Integer>> map = ints.boxed().collect(Collectors.groupingBy(i -> i / 2));
    Collection<List<Integer>> values = map.values();

    Function<? super List<Integer>, ? extends List<InlineButtonSpec>> mapper = indices -> {
      Function<? super Integer, ? extends InlineButtonSpec> mapper2 = i -> {
        String string = "view_team_" + pageItems.get(i);
        ByteBuf wrappedBuffer = Unpooled.wrappedBuffer(string.getBytes());
        return InlineButtonSpec.callback(pageItems.get(i), false, wrappedBuffer);
      };

      return indices.stream().map(mapper2).collect(Collectors.toList());
    };

    List<List<InlineButtonSpec>> rows = values.stream().map(mapper)
        //
        .collect(Collectors.toList());

    // Add pagination buttons
    if (page > 1) {
      String string = "page_" + (page - 1);
      ByteBuf wrappedBuffer = Unpooled.wrappedBuffer(string.getBytes());
      rows.add(List.of(InlineButtonSpec.callback("⬅️ 上一页", false, wrappedBuffer)));
    }
    if (page < totalPages) {
      String string = "page_" + (page + 1);
      ByteBuf wrappedBuffer = Unpooled.wrappedBuffer(string.getBytes());
      rows.add(List.of(InlineButtonSpec.callback("➡️ 下一页", false, wrappedBuffer)));
    }
    ByteBuf wrappedBuffer = Unpooled.wrappedBuffer("start".getBytes());

    rows.add(List.of(InlineButtonSpec.callback("🏠 返回首页", false, wrappedBuffer)));

    ReplyMarkupSpec markup = ReplyMarkupSpec.inlineKeyboard(rows);

    return event.getChat().sendMessage(SendMessageSpec.of("🚗 当前车队列表：").withReplyMarkup(markup));
  }

  // Show details of a specific team
  private Publisher<?> showTeamDetails(CallbackQueryEvent event, String teamId) {

    List<InlineButtonSpec> homePageButton = List.of(
        ///
        InlineButtonSpec.callback("🏠 返回首页", false, Unpooled.wrappedBuffer("start".getBytes())));
    List<InlineButtonSpec> returnListButton = List.of(
        //
        InlineButtonSpec.callback("⬅️ 返回列表", false, Unpooled.wrappedBuffer("page_1".getBytes())));
    
    List<List<InlineButtonSpec>> rows = List.of(homePageButton, returnListButton);

    ReplyMarkupSpec markup = ReplyMarkupSpec.inlineKeyboard(rows);

    String details = "🚗 车队详情：\n- 车队名：" + teamId + "\n- 成员数：10\n- 联系方式：123456789";
    return event.getChat().sendMessage(SendMessageSpec.of(details).withReplyMarkup(markup));
  }

  // Event Adapter
  public static class BotEventAdapter extends EventAdapter {
    private final BotMenuExample botMenuExample = new BotMenuExample();

    @Override
    public Publisher<?> onCallbackQuery(CallbackQueryEvent event) {
      return botMenuExample.onCallbackQuery(event);
    }

    @Override
    public Publisher<?> onSendMessage(SendMessageEvent event) {
      String message = event.getMessage().getContent();
      log.info("message:{}", message);
      try {
        Predicate<? super MessageEntity> predicate = p -> p.getType() == MessageEntity.Type.BOT_COMMAND &&
        //
            p.getContent().equals(message);

        List<MessageEntity> entities = event.getMessage().getEntities();

        Optional<? extends String> first = entities.stream().filter(predicate)
            //
            .map(MessageEntity::getContent).findFirst();
        if (first.isPresent()) {
          log.info("first:{}", first.get());
        }

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
      } catch (Exception e) {
        e.printStackTrace();
      }
      return null;
    }
  }
}
