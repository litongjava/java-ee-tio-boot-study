package com.litongjava.tio.web.hello.example;

import java.util.function.Predicate;

import com.litongjava.tio.utils.environment.EnvUtils;
import com.litongjava.tio.web.hello.func.PingFunctionV3;

import reactor.core.publisher.Mono;
import telegram4j.core.MTProtoBootstrap;
import telegram4j.core.MTProtoTelegramClient;
import telegram4j.core.event.domain.message.SendMessageEvent;

public class ExampleReplyBotV4 {
  public static void main(String[] args) {
    EnvUtils.load();

    int apiId = EnvUtils.getInt("telegram.api.id");
    String apiHash = EnvUtils.getStr("telegram.api.hash");
    String botAuthToken = EnvUtils.getStr("telegram.bot.auth.token");

    MTProtoBootstrap bootstrap = MTProtoTelegramClient.create(apiId, apiHash, botAuthToken);
    Mono<MTProtoTelegramClient> monoClient = bootstrap.connect();
    MTProtoTelegramClient client = monoClient.block();

    PingFunctionV3 pingFunction = new PingFunctionV3();

    client.on(SendMessageEvent.class)
        //
        .filter(e -> e.getMessage().getContent().equals("!ping"))
        //
        .flatMap(pingFunction::apply).subscribe();

    Predicate<? super SendMessageEvent> p = e -> e.getMessage().getContent().equals("!ping2");
    client.on(SendMessageEvent.class).filter(p).flatMap(pingFunction::apply).subscribe();

    client.onDisconnect().block();
  }
}