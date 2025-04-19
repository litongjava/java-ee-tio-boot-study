package com.litongjava.druid;

import com.litongjava.annotation.AComponentScan;
import com.litongjava.tio.boot.TioApplication;

@AComponentScan
public class DruidApp {
  public static void main(String[] args) {
    TioApplication.run(DruidApp.class, args);
  }
}
