package com.litongjava.tio.boot.hello.tcp.config;

import com.litongjava.annotation.AInitialization;
import com.litongjava.annotation.BeforeStartConfiguration;
import com.litongjava.tio.boot.hello.tcp.handler.DemoHandler;
import com.litongjava.tio.boot.hello.tcp.listener.DemoListener;
import com.litongjava.tio.boot.server.TioBootServer;

@BeforeStartConfiguration
public class TioBootServerConfig {

  @AInitialization
  public void config() {
    TioBootServer server = TioBootServer.me();
    DemoHandler demoHandler = new DemoHandler();
    server.setServerAioHandler(demoHandler);

    DemoListener demoListener = new DemoListener();
    server.setServerAioListener(demoListener);
  }
}