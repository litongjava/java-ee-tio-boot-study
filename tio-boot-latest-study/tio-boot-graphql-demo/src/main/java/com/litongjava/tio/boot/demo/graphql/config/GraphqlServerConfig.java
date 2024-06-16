package com.litongjava.tio.boot.demo.graphql.config;

import java.io.IOException;

import com.litongjava.tio.boot.context.TioBootConfiguration;

public class GraphqlServerConfig implements TioBootConfiguration {

  @Override
  public void config() throws IOException {
    new HttpRequestHanlderConfig().config();
    new GraphQLConfig().graphQL();
  }
}
