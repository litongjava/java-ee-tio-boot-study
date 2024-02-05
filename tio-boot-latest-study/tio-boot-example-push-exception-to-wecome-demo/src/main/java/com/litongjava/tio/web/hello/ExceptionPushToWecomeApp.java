package com.litongjava.tio.web.hello;

import com.litongjava.jfinal.aop.annotation.AComponentScan;
import com.litongjava.tio.boot.TioApplication;

@AComponentScan
public class ExceptionPushToWecomeApp {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    TioApplication.run(ExceptionPushToWecomeApp.class, args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "ms");
  }
}
