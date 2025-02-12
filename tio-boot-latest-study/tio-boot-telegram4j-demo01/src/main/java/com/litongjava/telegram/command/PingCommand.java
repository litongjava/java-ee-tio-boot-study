/*
 * Copyright 2023 Telegram4J
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.litongjava.telegram.command;

import java.util.function.Function;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import telegram4j.core.event.domain.message.SendMessageEvent;
import telegram4j.core.object.Message;
import telegram4j.core.object.chat.Chat;
import telegram4j.core.spec.EditMessageSpec;
import telegram4j.core.spec.EditMessageSpec.Builder;
import telegram4j.core.spec.ReplyToMessageSpec;
import telegram4j.core.spec.SendMessageSpec;

@TelegramCommand(command = "ping", description = "Pong!")
public class PingCommand implements Command {
  @Override
  public Publisher<?> execute(SendMessageEvent event) {
    Mono<Chat> monoChat = Mono.justOrEmpty(event.getChat()).switchIfEmpty(event.getMessage().getChat());

    Function<? super Chat, ? extends Mono<? extends Message>> transformer = c -> {
      long pre = System.currentTimeMillis();
      SendMessageSpec message = SendMessageSpec.of("Wait a second...")
          //
          .withReplyTo(ReplyToMessageSpec.of(event.getMessage()));

      Function<? super Message, ? extends Mono<? extends Message>> transformer2 = (m)->{
       Builder builder = EditMessageSpec.builder().message("Pong! " + (System.currentTimeMillis() - pre) + "ms.");
       Mono<Message> edit = m.edit(builder.build());
      return edit;
      };
      return c.sendMessage(message).flatMap(transformer2);
    };
    return monoChat.flatMap(transformer);
  }
}