package com.litongjava.tio.http.server;

import java.io.IOException;

import com.litongjava.tio.http.common.HttpConfig;
import com.litongjava.tio.http.common.handler.HttpRequestHandler;
import com.litongjava.tio.http.server.controller.IndexController;
import com.litongjava.tio.http.server.handler.HttpRoutes;
import com.litongjava.tio.http.server.handler.SimpleHttpDispatcherHandler;
import com.litongjava.tio.http.server.handler.SimpleHttpRoutes;
import com.litongjava.tio.server.ServerTioConfig;

public class MainApp {

  public static void main(String[] args) throws IOException {
    // 手动添加路由
    IndexController controller = new IndexController();
    HttpRoutes simpleHttpRoutes = new SimpleHttpRoutes();
    simpleHttpRoutes.add("/", controller::index);
    simpleHttpRoutes.add("/plaintext", controller::plaintext);
    simpleHttpRoutes.add("/json", controller::json);

    // config server
    HttpConfig httpConfig = new HttpConfig(80, null, null, null);
    // 关闭心跳
    HttpRequestHandler requestHandler = new SimpleHttpDispatcherHandler(httpConfig, simpleHttpRoutes);
    HttpServerStarter httpServerStarter = new HttpServerStarter(httpConfig, requestHandler);
    ServerTioConfig serverTioConfig = httpServerStarter.getServerTioConfig();

    serverTioConfig.setHeartbeatTimeout(0);
    // start server
    httpServerStarter.start();
  }

}