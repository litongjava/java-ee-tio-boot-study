package com.litongjava.tio.web.hello.config;

import com.litongjava.jfinal.aop.annotation.Configuration;
import com.litongjava.jfinal.aop.annotation.Initialization;

@Configuration
public class NoThingConfig {

  @Initialization
  public void init() {
    System.out.println("init");
  }

}
