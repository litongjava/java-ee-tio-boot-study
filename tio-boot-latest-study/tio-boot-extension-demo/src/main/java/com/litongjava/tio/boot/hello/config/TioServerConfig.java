package com.litongjava.tio.boot.hello.config;

import java.io.IOException;

import com.litongjava.jfinal.aop.annotation.Bean;
import com.litongjava.tio.boot.hello.tioserver.DemoTioServer;

//@Configuration
public class TioServerConfig {

  @Bean
  public DemoTioServer demoTioServer() {
    DemoTioServer demoTioServer = new DemoTioServer();
    try {
      demoTioServer.start();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return demoTioServer;
  }

}
