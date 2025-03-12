package com.litongjava.test;

import com.litongjava.annotation.AComponentScan;
import com.litongjava.tio.boot.TioApplication;

@AComponentScan
public class HelloApp {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    //TioApplicationWrapper.run(HelloApp.class, args);
    TioApplication.run(HelloApp.class, args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "ms");
  }
}
