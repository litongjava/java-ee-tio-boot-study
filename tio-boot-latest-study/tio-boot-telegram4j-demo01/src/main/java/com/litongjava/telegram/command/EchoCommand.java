package com.litongjava.telegram.command;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import telegram4j.core.event.domain.message.SendMessageEvent;
import telegram4j.core.object.chat.Chat;
import telegram4j.core.spec.ReplyToMessageSpec;
import telegram4j.core.spec.SendMessageSpec;
import telegram4j.core.spec.SendMessageSpec.Builder;

@TelegramCommand(command = "echo", description = "Repeat text.")
public class EchoCommand implements Command {
  @Override
  public Publisher<?> execute(SendMessageEvent event) {
    Mono<Chat> monoChat = Mono.justOrEmpty(event.getChat()).switchIfEmpty(event.getMessage().getChat());
    return monoChat.flatMap(c -> {
      String text = event.getMessage().getContent();
      int spc = text.indexOf(' ');
      Builder builder = SendMessageSpec.builder()
          //
          .message(spc == -1 ? "Missing echo text." : text.substring(spc + 1))
          //
          .replyTo(ReplyToMessageSpec.of(event.getMessage()));
      
      SendMessageSpec message = builder.build();
      return c.sendMessage(message);
    });
  }
}