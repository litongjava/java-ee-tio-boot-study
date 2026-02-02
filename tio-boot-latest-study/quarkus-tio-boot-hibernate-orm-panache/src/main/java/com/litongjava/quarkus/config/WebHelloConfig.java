package com.litongjava.quarkus.config;

import com.litongjava.context.BootConfiguration;
import com.litongjava.quarkus.handler.UserHandler;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.http.server.router.HttpRequestRouter;

public class WebHelloConfig implements BootConfiguration {

  public void config() {

    TioBootServer server = TioBootServer.me();
    HttpRequestRouter requestRouter = server.getRequestRouter();

    UserHandler userHandler = QuarkusBeans.get(UserHandler.class);
    requestRouter.add("/user/create", userHandler::createUser);
    requestRouter.add("/user/get", userHandler::getUser);
  }
}
