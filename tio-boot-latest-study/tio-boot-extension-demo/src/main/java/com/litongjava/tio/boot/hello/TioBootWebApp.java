package com.litongjava.tio.boot.hello;

import com.litongjava.hotswap.wrapper.tio.boot.TioApplicationWrapper;
import com.litongjava.jfinal.aop.annotation.AComponentScan;
import com.litongjava.tio.utils.quartz.QuartzUtils;

@AComponentScan
public class TioBootWebApp {
  public static void main(String[] args) throws Exception {
    long start = System.currentTimeMillis();
    QuartzUtils.start();
    TioApplicationWrapper.run(TioBootWebApp.class, args);
    long end = System.currentTimeMillis();
    System.out.println("started:" + (end - start) + "(ms)");
  }
}