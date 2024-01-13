package com.litongjava.tio.boot.hello.config;

import java.io.IOException;

import com.litongjava.jfinal.aop.annotation.ABean;
import com.litongjava.tio.boot.hello.tioserver.DemoTioServer;

//@Configuration
public class TioServerConfig {

  @ABean
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
