package com.litongjava.tio.web.hello.config;

import com.litongjava.annotation.AConfiguration;
import com.litongjava.annotation.Initialization;
import com.litongjava.magic.script.ScriptManager;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.http.server.router.HttpRequestRouter;

@AConfiguration
public class RequestHanlderConfig {

  @Initialization
  public void httpRoutes() {

    // 将simpleHttpRoutes添加到TioBootServer
    HttpRequestRouter requestRouter = TioBootServer.me().getRequestRouter();
    if (requestRouter != null) {
      requestRouter.add("/hi", (request) -> {
        String filename = "ms/web_hello.ms";
        return ScriptManager.executeClasspathScript(filename);

      });
    }
  }
}