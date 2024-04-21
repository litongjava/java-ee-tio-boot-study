package com.litongjava.tio.web.hello.config;

import com.litongjava.jfinal.aop.annotation.AInitialization;
import com.litongjava.jfinal.aop.annotation.BeforeStartConfiguration;
import com.litongjava.magic.script.ScriptManager;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.http.server.handler.SimpleHttpRoutes;

@BeforeStartConfiguration
public class HttpServerRequestHanlderConfig {

  @AInitialization
  public void httpRoutes() {

    // 创建simpleHttpRoutes
    SimpleHttpRoutes simpleHttpRoutes = new SimpleHttpRoutes();

    simpleHttpRoutes.add("/hi", (request) -> {
      String filename = "ms/web_hello.ms";
      return ScriptManager.executeClasspathScript(filename);

    });

    // 将simpleHttpRoutes添加到TioBootServer
    TioBootServer.me().setHttpRoutes(simpleHttpRoutes);
  }
}