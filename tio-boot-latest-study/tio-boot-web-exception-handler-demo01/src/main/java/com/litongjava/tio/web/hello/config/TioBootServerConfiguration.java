package com.litongjava.tio.web.hello.config;

import com.litongjava.jfinal.aop.annotation.AConfiguration;
import com.litongjava.jfinal.aop.annotation.AInitialization;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.web.hello.handler.GlobalExceptionHadler;

@AConfiguration
public class TioBootServerConfiguration {
  @AInitialization
  public void config() {
    TioBootServer.me().setExceptionHandler(new GlobalExceptionHadler());
  }
}
