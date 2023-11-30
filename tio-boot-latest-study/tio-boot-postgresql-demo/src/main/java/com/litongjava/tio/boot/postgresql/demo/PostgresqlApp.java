package com.litongjava.tio.boot.postgresql.demo;

import org.tio.utils.jfinal.P;

import com.litongjava.hotswap.wrapper.tio.boot.TioApplicationWrapper;
import com.litongjava.jfinal.aop.annotation.ComponentScan;

@ComponentScan
public class PostgresqlApp {

  public static void main(String[] args) throws Exception {
    long start = System.currentTimeMillis();
    P.use("app.properties");
//    Context context = TioApplication.run(TioBootWebApp.class, args);
    TioApplicationWrapper.run(PostgresqlApp.class, args);
    long end = System.currentTimeMillis();
    System.out.println("started:" + (end - start) + "(ms)");
  }
}