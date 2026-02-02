package com.litongjava.spring.boot.tio.boot.demo01;

import com.litongjava.hotswap.wrapper.tio.boot.TioApplicationWrapper;
import com.litongjava.jfinal.aop.annotation.AComponentScan;

@AComponentScan
public class Applicaton {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    // 启动tio-boot
    TioApplicationWrapper.run(Applicaton.class, args);
    long end = System.currentTimeMillis();
    System.out.println(end - start + "(ms)");
  }
}
