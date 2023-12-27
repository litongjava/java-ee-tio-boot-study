package com.litongjava.tio.web.hello;

import com.litongjava.jfinal.aop.annotation.ComponentScan;
import com.litongjava.tio.boot.TioApplication;

@ComponentScan
public class TioCacheApp {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    TioApplication.run(TioCacheApp.class, args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "(ms)");
  }
}
