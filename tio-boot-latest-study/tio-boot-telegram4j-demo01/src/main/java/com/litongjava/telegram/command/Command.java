package com.litongjava.telegram.command;

import java.util.Objects;
import java.util.function.Supplier;

import org.reactivestreams.Publisher;

import telegram4j.core.event.domain.message.SendMessageEvent;
import telegram4j.tl.BotCommand;
import telegram4j.tl.ImmutableBotCommand;

public interface Command {

  Publisher<?> execute(SendMessageEvent event);

  default BotCommand getInfo() {
    TelegramCommand info = getClass().getDeclaredAnnotation(TelegramCommand.class);
    Supplier<String> messageSupplier = () -> {
      String canonicalName = getClass().getCanonicalName();
      return "No @TelegramCommand annotation present on " + canonicalName;
    };
    Objects.requireNonNull(info, messageSupplier);
    return ImmutableBotCommand.of(info.command(), info.description());
  }
}