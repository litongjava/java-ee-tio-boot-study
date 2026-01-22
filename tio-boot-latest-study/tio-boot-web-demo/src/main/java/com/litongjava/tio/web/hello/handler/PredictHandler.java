package com.litongjava.tio.web.hello.handler;

import java.util.ArrayList;
import java.util.List;

import com.litongjava.chat.PlatformInput;
import com.litongjava.chat.UniChatClient;
import com.litongjava.chat.UniChatMessage;
import com.litongjava.chat.UniChatRequest;
import com.litongjava.chat.UniChatResponse;
import com.litongjava.consts.ModelPlatformName;
import com.litongjava.openrouter.OpenRouterModels;
import com.litongjava.tio.boot.http.TioRequestContext;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.server.handler.HttpRequestHandler;

public class PredictHandler implements HttpRequestHandler {

  @Override
  public HttpResponse handle(HttpRequest httpRequest) throws Exception {
    PlatformInput platform = new PlatformInput(ModelPlatformName.OPENROUTER,
        OpenRouterModels.XIAOMI_MIMO_V2_FLASH_FREE);

    List<UniChatMessage> messges = new ArrayList<>();
    messges.add(UniChatMessage.buildUser("just say hi"));

    UniChatRequest uniChatRequest = new UniChatRequest(platform);
    uniChatRequest.setMessages(messges);

    UniChatResponse uniChatResponse = UniChatClient.generate(uniChatRequest);

    String content = uniChatResponse.getMessage().getContent();

    HttpResponse response = TioRequestContext.getResponse();
    return response.setBody(content);
  }
}
