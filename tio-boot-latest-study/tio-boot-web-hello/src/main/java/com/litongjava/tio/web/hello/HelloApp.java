package com.litongjava.tio.web.hello;

import com.litongjava.hotswap.wrapper.tio.boot.TioApplicationWrapper;
import com.litongjava.jfinal.aop.annotation.ComponentScan;
import com.litongjava.tio.boot.TioApplication;

@ComponentScan
public class HelloApp {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    // TioApplicationWrapper.run(HelloApp.class, args);
    TioApplication.run(HelloApp.class, args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "ms");
  }
}