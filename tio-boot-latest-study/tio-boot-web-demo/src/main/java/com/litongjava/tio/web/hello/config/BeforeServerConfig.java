package com.litongjava.tio.web.hello.config;

import com.litongjava.annotation.BeforeStartConfiguration;
import com.litongjava.annotation.Initialization;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.web.hello.handler.MyDecodeExceptionHandler;

@BeforeStartConfiguration
public class BeforeServerConfig {

  @Initialization
  public void config() {
    MyDecodeExceptionHandler myDecodeExceptionHandler = new MyDecodeExceptionHandler();
    TioBootServer.me().setDecodeExceptionHandler(myDecodeExceptionHandler);

  }
}
