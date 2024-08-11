package com.litongjava.tio.web.hello;

import com.litongjava.jfinal.aop.annotation.AComponentScan;
import com.litongjava.tio.boot.TioApplication;

@AComponentScan
public class ConsumerApp {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    TioApplication.run(ConsumerApp.class, args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "ms");
  }
}
