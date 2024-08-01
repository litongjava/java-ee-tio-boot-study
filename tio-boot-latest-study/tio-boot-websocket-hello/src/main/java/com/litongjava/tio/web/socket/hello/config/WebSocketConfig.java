package com.litongjava.tio.web.socket.hello.config;

import com.litongjava.jfinal.aop.annotation.AConfiguration;
import com.litongjava.jfinal.aop.annotation.AInitialization;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.boot.websocket.handler.WebSocketRoutes;
import com.litongjava.tio.web.socket.hello.handler.HelloWebSocketHandler;

@AConfiguration
public class WebSocketConfig {

  @AInitialization
  public void config() {
    WebSocketRoutes webSocketRoutes = new WebSocketRoutes();
    webSocketRoutes.add("/hello", new HelloWebSocketHandler());
    // 添加到TioBootServer,为了保证高性能,默认webSocketRoutes为null,必须添加
    TioBootServer.me().setWebSocketRoutes(webSocketRoutes);
  }
}
