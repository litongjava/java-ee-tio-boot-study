package com.litongjava.tio.web.hello.config;

import com.litongjava.annotation.AConfiguration;
import com.litongjava.annotation.Initialization;
import com.litongjava.tio.boot.http.handler.common.WebjarHandler;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.http.server.router.HttpRequestRouter;
import com.litongjava.tio.web.hello.controller.SwaggerUiHandler;
import com.litongjava.tio.web.hello.handler.HelloHandler;

@AConfiguration
public class WebHelloConfig {

  @Initialization
  public void config() {

    TioBootServer server = TioBootServer.me();
    HttpRequestRouter requestRouter = server.getRequestRouter();
    if (requestRouter != null) {
      SwaggerUiHandler swggerUiHander = new SwaggerUiHandler();
      requestRouter.add("/doc.html", swggerUiHander::html);
      WebjarHandler webjarHandler = new WebjarHandler();
      requestRouter.add("/webjars/**", webjarHandler::index);
      HelloHandler helloHandler = new HelloHandler();
      requestRouter.add("/hello", helloHandler);
    }
  }

}
