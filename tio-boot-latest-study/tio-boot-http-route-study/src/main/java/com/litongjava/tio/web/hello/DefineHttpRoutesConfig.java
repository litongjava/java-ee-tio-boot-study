package com.litongjava.tio.web.hello;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.jfinal.aop.annotation.AInitialization;
import com.litongjava.jfinal.aop.annotation.BeforeStartConfiguration;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.http.server.handler.SimpleHttpRoutes;

@BeforeStartConfiguration
public class DefineHttpRoutesConfig {

  @AInitialization
  public void httpRoutes() {
    // 创建simpleHttpRoutes
    SimpleHttpRoutes simpleHttpRoutes = new SimpleHttpRoutes();
    // 创建controller
    HelloController helloController = Aop.get(HelloController.class);

    // 添加action
    simpleHttpRoutes.add("/hi", helloController::hi);
    simpleHttpRoutes.add("/hello", helloController::hello);

    // 将simpleHttpRoutes添加到TioBootServer
    TioBootServer.setHttpRoutes(simpleHttpRoutes);
  }
}
