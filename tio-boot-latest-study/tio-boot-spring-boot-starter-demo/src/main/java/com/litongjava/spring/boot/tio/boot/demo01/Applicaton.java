package com.litongjava.spring.boot.tio.boot.demo01;

import com.litongjava.annotation.AComponentScan;
import com.litongjava.tio.boot.TioApplication;

@AComponentScan
public class Applicaton {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    // 启动tio-boot
    TioApplication.run(Applicaton.class, args);
    long end = System.currentTimeMillis();
    System.out.println(end - start + "(ms)");
  }
}
