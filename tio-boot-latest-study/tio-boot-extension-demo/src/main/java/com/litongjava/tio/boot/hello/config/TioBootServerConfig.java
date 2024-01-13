package com.litongjava.tio.boot.hello.config;

import com.litongjava.jfinal.aop.annotation.AInitialization;
import com.litongjava.jfinal.aop.annotation.BeforeStartConfiguration;
import com.litongjava.tio.boot.hello.tcp.DemoHandler;
import com.litongjava.tio.boot.hello.tcp.DemoListener;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.boot.tcp.ServerTcpHandler;

@BeforeStartConfiguration
public class TioBootServerConfig {

  @AInitialization
  public void config() {
    ServerTcpHandler demoHandler = new DemoHandler();
    TioBootServer.setServerTcpHandler(demoHandler);

    DemoListener demoListener = new DemoListener();
    TioBootServer.setServerAioListener(demoListener);
  }
}
