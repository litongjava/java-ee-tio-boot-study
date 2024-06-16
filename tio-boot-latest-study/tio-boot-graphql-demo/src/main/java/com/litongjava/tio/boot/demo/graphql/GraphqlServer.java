package com.litongjava.tio.boot.demo.graphql;

import com.litongjava.jfinal.aop.annotation.AComponentScan;
import com.litongjava.tio.boot.TioApplication;
import com.litongjava.tio.boot.demo.graphql.config.GraphqlServerConfig;

@AComponentScan
public class GraphqlServer {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    TioApplication.run(GraphqlServer.class, new GraphqlServerConfig(), args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "ms");
  }
}
