package com.litongjava.tio.web.hello.func;

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
public class PingFunctionV3 {

  public Publisher<? extends Message> apply(SendMessageEvent event) {
    // Attempt to retrieve the chat from the event. If absent, retrieve it from the message.
    Mono<Chat> monoChat = Mono.justOrEmpty(event.getChat()).switchIfEmpty(event.getMessage().getChat());

    Mono<Message> monoMessage = monoChat.flatMap((chat) -> {
      // Create a specification for replying to the original message
      Message message = event.getMessage();
      ReplyToMessageSpec replySpec = ReplyToMessageSpec.of(message);
      // Create a specification for the "pong!" message with the reply spec
      SendMessageSpec messageSpec = SendMessageSpec.of("pong!").withReplyTo(replySpec);

      // Send the message using the chat's sendMessage method
      return chat.sendMessage(messageSpec);
    });
    
    return monoMessage;
  }
}