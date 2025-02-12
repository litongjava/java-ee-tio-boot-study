package com.litongjava.tio.web.hello.example;

import java.nio.file.Path;
import java.time.Duration;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Function;

import org.reactivestreams.Publisher;

import com.litongjava.tio.utils.environment.EnvUtils;

import io.netty.util.ResourceLeakDetector;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;
import telegram4j.core.MTProtoBootstrap;
import telegram4j.core.MTProtoTelegramClient;
import telegram4j.core.event.domain.inline.InlineQueryEvent;
import telegram4j.core.event.domain.message.SendMessageEvent;
import telegram4j.core.object.MessageMedia;
import telegram4j.core.spec.AnswerInlineCallbackQuerySpec;
import telegram4j.core.spec.AnswerInlineCallbackQuerySpec.Builder;
import telegram4j.core.spec.AnswerInlineCallbackQuerySpec.Flag;
import telegram4j.core.spec.inline.InlineMessageSpec;
import telegram4j.core.spec.inline.InlineResultArticleSpec;
import telegram4j.core.spec.inline.InlineResultDocumentSpec;
import telegram4j.core.spec.markup.InlineButtonSpec;
import telegram4j.core.spec.markup.ReplyMarkupSpec;
import telegram4j.core.util.parser.EntityParserFactory;
import telegram4j.mtproto.file.FileReferenceId;
import telegram4j.mtproto.store.FileStoreLayout;
import telegram4j.mtproto.store.StoreLayoutImpl;

@Slf4j
public class MTProtoBotInlineExample {

  private static final Duration CACHE_TIME = Duration.ofSeconds(30);
  private static final Duration PHOTO_CACHE_TIME = Duration.ofSeconds(5);

  private static volatile FileReferenceId lastPhotoId;

  public static void main(String[] args) {
    EnvUtils.load();
    // only for testing, do not copy it to your production code!!!
    Hooks.onOperatorDebug();
    ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.PARANOID);

    int apiId = EnvUtils.getInt("telegram.api.id");
    String apiHash = EnvUtils.getStr("telegram.api.hash");
    String botAuthToken = EnvUtils.getStr("telegram.bot.auth.token");

    // Don't forget to enable inline queries in the @BotFather settings
    MTProtoBootstrap bootstrap = MTProtoTelegramClient.create(apiId, apiHash, botAuthToken);

    FileStoreLayout storeLayout = new FileStoreLayout(new StoreLayoutImpl(Function.identity()), Path.of("MTProtoBotInlineExample.bin"));

    Function<MTProtoTelegramClient, ? extends Publisher<?>> func = client -> {

      Mono<Void> monoVoid = client.on(InlineQueryEvent.class).flatMap(MTProtoBotInlineExample::handleInlineQuery).then();

      // listen all messages with documents/photos to update lastPhotoId
      Mono<Void> listenIncomingFri = client.on(SendMessageEvent.class)
          //
          .flatMap(s -> Mono.justOrEmpty(s.getMessage().getMedia()))
          //
          .ofType(MessageMedia.Document.class)
          //
          .flatMap(d -> Mono.justOrEmpty(d.getDocument()))
          //
          .doOnNext(s -> lastPhotoId = s.getFileReferenceId()).then();

      return Mono.when(monoVoid, listenIncomingFri);
    };

    bootstrap.setStoreLayout(storeLayout).withConnection(func).block();
  }

  private static Publisher<?> handleInlineQuery(InlineQueryEvent e) {
    String lowerCase = e.getQuery().toLowerCase();
    log.info("query:{}", lowerCase);

    switch (lowerCase) {
    case "article" -> {
      String text = "[Telegram wikipedia page.](https://en.wikipedia.org/wiki/Telegram_\\(software\\))";
      InlineMessageSpec message = InlineMessageSpec.text(text).withParser(EntityParserFactory.MARKDOWN_V2);

      InlineResultArticleSpec articleSpec = InlineResultArticleSpec.builder()
          //
          .id("1").title("Telegram wikipedia page.")
          //
          .url("https://en.wikipedia.org/wiki/Telegram_(software)")
          //
          .message(message)
          //
          .build();

      Builder builder = AnswerInlineCallbackQuerySpec.builder();
      builder.flags(EnumSet.noneOf(Flag.class));
      builder.cacheTime(CACHE_TIME);
      builder.addResult(articleSpec);

      AnswerInlineCallbackQuerySpec callbackQuerySpec = builder.build();

      return e.answer(callbackQuerySpec);
    }
    case "gif" -> {
      InlineButtonSpec buttonNo = InlineButtonSpec.url("no!", "https://www.youtube.com/watch?v=dQw4w9WgXcQ");
      InlineButtonSpec buttonYes = InlineButtonSpec.url("yes!", "https://www.youtube.com/watch?v=zvq9r6R6QAY");
      ReplyMarkupSpec inlineKeyboard = ReplyMarkupSpec.inlineKeyboard(List.of(List.of(buttonYes, buttonNo)));

      InlineMessageSpec message = InlineMessageSpec.mediaAuto("<i>Cute gif animation, isn't it?</i>")
          //
          .withParser(EntityParserFactory.HTML)
          //
          .withReplyMarkup(inlineKeyboard);

      InlineResultDocumentSpec result = InlineResultDocumentSpec.builder()
          //
          .id("4").type(FileReferenceId.DocumentType.GIF).title("Niko caramelldansen!")
          //
          .document("https://media.tenor.com/VqUFZ4uNMCoAAAAC/niko-dance-one-shot-dancing.gif")
          //
          .size(498, 373).mimeType("image/gif").duration(Duration.ofMillis(800))
          //
          .message(message).build();

      Builder builder = AnswerInlineCallbackQuerySpec.builder();
      AnswerInlineCallbackQuerySpec callbackQuerySpec = builder
          //
          .flags(EnumSet.noneOf(Flag.class)).cacheTime(CACHE_TIME)
          //
          .addResult(result).build();

      return e.answer(callbackQuerySpec);
    }
    case "photo" -> {
      log.info("eneter phtoto");
      InlineMessageSpec message = InlineMessageSpec.mediaAuto("Icon of TDesktop");
      String documentUrl = "https://raw.githubusercontent.com/telegramdesktop/tdesktop/dev/Telegram/Resources/art/icon256%402x.png";

      InlineResultDocumentSpec result = InlineResultDocumentSpec.builder()
          //
          .id("5").type(FileReferenceId.DocumentType.PHOTO).size(256, 256)
          //
          .document(documentUrl).message(message)
          //
          .build();

      Builder builder = AnswerInlineCallbackQuerySpec.builder();
      AnswerInlineCallbackQuerySpec callbackQuerySpec = builder
          //
          .cacheTime(CACHE_TIME).flags(EnumSet.noneOf(Flag.class))
          //
          .addResult(result).build();
      return e.answer(callbackQuerySpec);
    }

    case "lastphoto" -> {
      FileReferenceId photoId = lastPhotoId;
      if (photoId == null) {
        return Mono.empty();
      }
      InlineMessageSpec message = InlineMessageSpec.mediaAuto("_Hmm... It's a last photo which I saw_")
          //
          .withParser(EntityParserFactory.MARKDOWN_V2);

      InlineResultDocumentSpec result = InlineResultDocumentSpec.builder().id("4")
          //
          .document(photoId).message(message).build();

      Builder builder = AnswerInlineCallbackQuerySpec.builder();
      AnswerInlineCallbackQuerySpec callbackQuerySpec = builder
          //
          .cacheTime(PHOTO_CACHE_TIME).flags(EnumSet.noneOf(Flag.class))
          //
          .addResult(result).build();
      return e.answer(callbackQuerySpec);
    }
    default -> {
      log.info("empty");
      return Mono.empty();
    }
    }
  }
}