package com.litongjava.tio.dubbo.demo001;

import com.litongjava.jfinal.aop.annotation.AComponentScan;
import com.litongjava.tio.boot.TioApplication;

@AComponentScan
public class ProviderApp {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    TioApplication.run(ProviderApp.class, args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "ms");
  }
}