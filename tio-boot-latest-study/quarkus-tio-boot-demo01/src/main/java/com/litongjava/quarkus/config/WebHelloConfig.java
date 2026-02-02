package com.litongjava.quarkus.config;

import com.litongjava.context.BootConfiguration;
import com.litongjava.quarkus.handler.HelloHandler;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.http.server.router.HttpRequestRouter;

public class WebHelloConfig implements BootConfiguration {

  public void config() {

    TioBootServer server = TioBootServer.me();
    HttpRequestRouter requestRouter = server.getRequestRouter();

    HelloHandler helloHandler = QuarkusBeans.get(HelloHandler.class);
    requestRouter.add("/hello", helloHandler);
  }
}
