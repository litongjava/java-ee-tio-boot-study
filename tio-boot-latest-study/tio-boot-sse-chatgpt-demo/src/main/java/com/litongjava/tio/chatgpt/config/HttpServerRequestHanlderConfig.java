package com.litongjava.tio.chatgpt.config;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.jfinal.aop.annotation.AInitialization;
import com.litongjava.jfinal.aop.annotation.BeforeStartConfiguration;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.chatgpt.handler.OpenaiV1ChatHandler;
import com.litongjava.tio.http.server.router.SimpleHttpRoutes;

@BeforeStartConfiguration
public class HttpServerRequestHanlderConfig {

  @AInitialization
  public void httpRoutes() {

    // 创建simpleHttpRoutes
    SimpleHttpRoutes simpleHttpRoutes = new SimpleHttpRoutes();
    // 创建controller
    OpenaiV1ChatHandler openaiV1ChatCompletionsHandler = Aop.get(OpenaiV1ChatHandler.class);

    // 添加action
    simpleHttpRoutes.add("/openai/v1/chat/completions", openaiV1ChatCompletionsHandler::completions);

    // 将simpleHttpRoutes添加到TioBootServer
    TioBootServer.me().setHttpRoutes(simpleHttpRoutes);
  }

}
