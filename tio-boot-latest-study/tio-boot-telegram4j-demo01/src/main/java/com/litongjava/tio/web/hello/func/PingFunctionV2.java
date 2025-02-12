package com.litongjava.tio.web.hello.func;

import java.util.function.Function;

import org.reactivestreams.Publisher;

import reactor.core.publisher.Mono;
import telegram4j.core.event.domain.message.SendMessageEvent;
import telegram4j.core.object.Message;
import telegram4j.core.object.chat.Chat;
import telegram4j.core.spec.ReplyToMessageSpec;
import telegram4j.core.spec.SendMessageSpec;

/**
 * PingFunction processes a SendMessageEvent and replies with "pong!".
 */
public class PingFunctionV2 implements Function<SendMessageEvent, Publisher<? extends Message>> {

  @Override
  public Publisher<? extends Message> apply(SendMessageEvent event) {
    // Attempt to retrieve the chat from the event. If absent, retrieve it from the message.
    Mono<Chat> chatMono = Mono.justOrEmpty(event.getChat()).switchIfEmpty(event.getMessage().getChat());

    // Define a transformer that sends a "pong!" message in reply.
    Function<Chat, Mono<? extends Message>> transformer = new Function<Chat, Mono<? extends Message>>() {
      @Override
      public Mono<? extends Message> apply(Chat chat) {
        // Create a specification for replying to the original message
        ReplyToMessageSpec replySpec = ReplyToMessageSpec.of(event.getMessage());

        // Create a specification for the "pong!" message with the reply spec
        SendMessageSpec messageSpec = SendMessageSpec.of("pong!").withReplyTo(replySpec);

        // Send the message using the chat's sendMessage method
        return chat.sendMessage(messageSpec);
      }
    };

    // Apply the transformer to the retrieved chat
    return chatMono.flatMap(transformer);
  }
}