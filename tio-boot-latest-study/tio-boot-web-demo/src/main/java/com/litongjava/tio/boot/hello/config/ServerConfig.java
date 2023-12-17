package com.litongjava.tio.boot.hello.config;

import com.litongjava.jfinal.aop.annotation.Bean;
import com.litongjava.jfinal.aop.annotation.BeforeStartConfiguration;
import com.litongjava.tio.boot.hello.tcp.DemoHandler;
import com.litongjava.tio.boot.hello.tcp.DemoListener;
import com.litongjava.tio.boot.tcp.ServerListener;
import com.litongjava.tio.boot.tcp.ServerTcpHandler;

@BeforeStartConfiguration
public class ServerConfig {

  @Bean
  public ServerTcpHandler demoHandler() {
    ServerTcpHandler demoHandler = new DemoHandler();
    return demoHandler;
  }
  
  @Bean
  public ServerListener serverListener() {
    return new DemoListener();
  }
}
