package com.litongjava.tio.web.hello.handler;

import java.util.ArrayList;
import java.util.List;

import com.litongjava.chat.PlatformInput;
import com.litongjava.chat.UniChatClient;
import com.litongjava.chat.UniChatEventListener;
import com.litongjava.chat.UniChatMessage;
import com.litongjava.chat.UniChatRequest;
import com.litongjava.chat.UniChatResponse;
import com.litongjava.consts.ModelPlatformName;
import com.litongjava.openrouter.OpenRouterModels;
import com.litongjava.tio.boot.http.TioRequestContext;
import com.litongjava.tio.core.ChannelContext;
import com.litongjava.tio.core.Tio;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.common.sse.SsePacket;
import com.litongjava.tio.http.server.handler.HttpRequestHandler;
import com.litongjava.tio.http.server.util.SseEmitter;

import okhttp3.Response;
import okhttp3.sse.EventSource;

public class PredictStreamHandler implements HttpRequestHandler {

  @Override
  public HttpResponse handle(HttpRequest httpRequest) throws Exception {
    PlatformInput platform = new PlatformInput(ModelPlatformName.OPENROUTER,
        OpenRouterModels.XIAOMI_MIMO_V2_FLASH_FREE);

    List<UniChatMessage> messges = new ArrayList<>();
    messges.add(UniChatMessage.buildUser("just say hi"));

    UniChatRequest uniChatRequest = new UniChatRequest(platform);
    uniChatRequest.setMessages(messges);

    HttpResponse httpResponse = TioRequestContext.getResponse();
    httpResponse.setSend(false);
    ChannelContext ctx = httpRequest.channelContext;
    UniChatEventListener listener = new UniChatEventListener() {

      @Override
      public void onOpen(EventSource eventSource, Response response) {
        httpResponse.addServerSentEventsHeader();
        Tio.send(ctx, httpResponse);
      }

      @Override
      public void onData(UniChatResponse chatResposne) {
        String content = chatResposne.getDelta().getContent();
        Tio.bSend(ctx, new SsePacket(content));
      }

      @Override
      public void onClosed(EventSource eventSource) {
        SseEmitter.closeSeeConnection(ctx);
      }
    };

    UniChatClient.stream(uniChatRequest, listener);

    return httpResponse;
  }
}
