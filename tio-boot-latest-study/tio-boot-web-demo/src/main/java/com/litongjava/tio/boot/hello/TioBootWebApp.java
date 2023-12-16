  package com.litongjava.tio.boot.hello;

import com.litongjava.hotswap.wrapper.tio.boot.TioApplicationWrapper;
import com.litongjava.jfinal.aop.annotation.ComponentScan;
import com.litongjava.tio.utils.jfinal.P;

@ComponentScan
public class TioBootWebApp {

  public static void main(String[] args) throws Exception {
    long start = System.currentTimeMillis();
    P.use("app.properties");
//    TioApplication.run(TioBootWebApp.class, args);
    TioApplicationWrapper.run(TioBootWebApp.class, args);
    long end = System.currentTimeMillis();
    System.out.println("started:" + (end - start) + "(ms)");
  }
}