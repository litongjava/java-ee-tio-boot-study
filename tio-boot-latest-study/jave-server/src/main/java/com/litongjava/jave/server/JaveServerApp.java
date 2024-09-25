package com.litongjava.jave.server;

import com.litongjava.jfinal.aop.annotation.AComponentScan;
import com.litongjava.tio.boot.TioApplication;

@AComponentScan
public class JaveServerApp {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    TioApplication.run(JaveServerApp.class, args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "ms");
  }
}
