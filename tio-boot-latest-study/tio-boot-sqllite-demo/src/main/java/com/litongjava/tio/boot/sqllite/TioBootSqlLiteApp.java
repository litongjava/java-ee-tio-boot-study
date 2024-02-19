package com.litongjava.tio.boot.sqllite;

import com.litongjava.hotswap.wrapper.tio.boot.TioApplicationWrapper;
import com.litongjava.jfinal.aop.annotation.AComponentScan;

@AComponentScan
public class TioBootSqlLiteApp {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    TioApplicationWrapper.run(TioBootSqlLiteApp.class, args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "ms");
  }

}
