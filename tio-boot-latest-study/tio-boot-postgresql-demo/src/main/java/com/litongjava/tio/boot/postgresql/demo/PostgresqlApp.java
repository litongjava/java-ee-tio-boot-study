package com.litongjava.tio.boot.postgresql.demo;

import com.litongjava.annotation.AComponentScan;
import com.litongjava.tio.boot.TioApplication;

@AComponentScan
public class PostgresqlApp {

  public static void main(String[] args) throws Exception {
    long start = System.currentTimeMillis();
    TioApplication.run(PostgresqlApp.class, args);
    long end = System.currentTimeMillis();
    System.out.println("started:" + (end - start) + "(ms)");
  }
}