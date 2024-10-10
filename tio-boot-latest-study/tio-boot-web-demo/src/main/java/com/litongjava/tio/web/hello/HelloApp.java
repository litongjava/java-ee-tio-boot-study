package com.litongjava.tio.web.hello;

import com.litongjava.annotation.AComponentScan;
import com.litongjava.tio.boot.TioApplication;

@AComponentScan
public class HelloApp {
  public static void main(String[] args) {
    TioApplication.run(HelloApp.class, args);
  }
}
