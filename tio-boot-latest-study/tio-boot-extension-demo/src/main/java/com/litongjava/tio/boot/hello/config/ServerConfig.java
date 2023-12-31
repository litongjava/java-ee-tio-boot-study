package com.litongjava.tio.boot.hello.config;

import com.litongjava.jfinal.aop.annotation.Bean;
import com.litongjava.jfinal.aop.annotation.BeforeStartConfiguration;
import com.litongjava.tio.boot.hello.tcp.DemoHandler;
import com.litongjava.tio.boot.hello.tcp.DemoListener;
import com.litongjava.tio.boot.tcp.ServerHanlderListener;
import com.litongjava.tio.boot.tcp.ServerTcpHandler;

@BeforeStartConfiguration
public class ServerConfig {

  @Bean
  public ServerTcpHandler demoHandler() {
    ServerTcpHandler demoHandler = new DemoHandler();
    return demoHandler;
  }
  
  @Bean
  public ServerHanlderListener serverListener() {
    return new DemoListener();
  }
}
