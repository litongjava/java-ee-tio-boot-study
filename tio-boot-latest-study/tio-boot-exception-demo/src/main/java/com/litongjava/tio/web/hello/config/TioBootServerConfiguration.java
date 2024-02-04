package com.litongjava.tio.web.hello.config;

import com.litongjava.jfinal.aop.annotation.AConfiguration;
import com.litongjava.jfinal.aop.annotation.AInitialization;
import com.litongjava.tio.boot.exception.TioBootExceptionHandler;
import com.litongjava.tio.boot.server.TioBootServer;

@AConfiguration
public class TioBootServerConfiguration {

  @AInitialization
  public void config() {
    TioBootExceptionHandler exceptionHandler = new MyExceptionHandler();
    TioBootServer.me().setExceptionHandler(exceptionHandler);
  }
}
