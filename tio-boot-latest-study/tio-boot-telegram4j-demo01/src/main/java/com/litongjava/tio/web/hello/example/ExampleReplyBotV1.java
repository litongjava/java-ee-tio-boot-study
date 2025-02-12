package com.litongjava.tio.web.hello.example;

import java.util.function.Function;
import java.util.function.Predicate;

import org.reactivestreams.Publisher;

import com.litongjava.tio.utils.environment.EnvUtils;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import telegram4j.core.MTProtoTelegramClient;
import telegram4j.core.event.domain.message.SendMessageEvent;
import telegram4j.core.object.Message;
import telegram4j.core.object.chat.Chat;
import telegram4j.core.spec.ReplyToMessageSpec;
import telegram4j.core.spec.SendMessageSpec;

@Slf4j
public class ExampleReplyBotV1 {
  public static void main(String[] args) {
    EnvUtils.load();

    int apiId = EnvUtils.getInt("telegram.api.id");
    String apiHash = EnvUtils.getStr("telegram.api.hash");
    String botAuthToken = EnvUtils.getStr("telegram.bot.auth.token");

    MTProtoTelegramClient client = MTProtoTelegramClient.create(apiId, apiHash, botAuthToken).connect().block();

    // 使用匿名内部类实现 Predicate
    Predicate<SendMessageEvent> p = new Predicate<SendMessageEvent>() {
      @Override
      public boolean test(SendMessageEvent e) {
        log.info("e:{}", e);
        return e.getMessage().getContent().equals("!ping");
      }
    };

    // Using anonymous inner class for the mapper
    Function<? super SendMessageEvent, ? extends Publisher<? extends Message>> mapper = new Function<SendMessageEvent, Publisher<? extends Message>>() {
      
      @Override
      public Publisher<? extends Message> apply(SendMessageEvent e) {
        Mono<Chat> mono = Mono.justOrEmpty(e.getChat()).switchIfEmpty(e.getMessage().getChat());

        Function<? super Chat, ? extends Mono<? extends Message>> transformer = new Function<Chat, Mono<? extends Message>>() {
          @Override
          public Mono<? extends Message> apply(Chat c) {
            ReplyToMessageSpec replyMessage = ReplyToMessageSpec.of(e.getMessage());
            SendMessageSpec message = SendMessageSpec.of("pong!").withReplyTo(replyMessage);
            return c.sendMessage(message);
          }
        };

        Mono<Message> flatMap = mono.flatMap(transformer);
        
        return flatMap;
      }
    };

    client.on(SendMessageEvent.class).filter(p).flatMap(mapper).subscribe();

    client.onDisconnect().block();
  }
}