package com.litongjava.tio.boot.demo.graphql.config;

import com.litongjava.tio.boot.demo.graphql.handler.GraphQLHandler;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.http.server.router.HttpReqeustSimpleHandlerRoute;

public class HttpRequestHanlderConfig {

  public void config() {
    // 获取router
    HttpReqeustSimpleHandlerRoute r = TioBootServer.me().getHttpReqeustSimpleHandlerRoute();
    GraphQLHandler graphQLHandler = new GraphQLHandler();

    r.add("/graphql", graphQLHandler::graphql);

  }
}
