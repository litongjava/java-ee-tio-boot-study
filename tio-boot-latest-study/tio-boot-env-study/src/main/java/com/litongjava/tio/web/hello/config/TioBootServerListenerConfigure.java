package com.litongjava.tio.web.hello.config;

import com.litongjava.jfinal.aop.annotation.Bean;
import com.litongjava.jfinal.aop.annotation.BeforeStartConfiguration;
import com.litongjava.tio.boot.server.TioBootServerListener;

@BeforeStartConfiguration
public class TioBootServerListenerConfigure {

  @Bean
  public TioBootServerListener tioBootServerListener() {
    return new MyServerListener();
  }
}
