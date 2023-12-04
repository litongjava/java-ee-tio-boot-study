package com.litongjava.tio.web.socket.hello.config;

import com.litongjava.jfinal.aop.annotation.Bean;
import com.litongjava.jfinal.aop.annotation.Configuration;
import com.litongjava.tio.boot.websockethandler.WebSocketRoutes;
import com.litongjava.tio.web.socket.hello.handler.HelloWebSocketHandler;

@Configuration
public class WebSocketConfig {

  @Bean
  public WebSocketRoutes webSocketRoutes() {
    WebSocketRoutes webSocketRoutes = new WebSocketRoutes();
    webSocketRoutes.add("/hello", HelloWebSocketHandler.class);
    return webSocketRoutes;
  }

}
