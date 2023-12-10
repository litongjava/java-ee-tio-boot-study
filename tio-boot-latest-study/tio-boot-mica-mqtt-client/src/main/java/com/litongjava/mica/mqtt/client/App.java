package com.litongjava.mica.mqtt.client;

import com.litongjava.hotswap.wrapper.tio.boot.TioApplicationWrapper;
import com.litongjava.jfinal.aop.annotation.ComponentScan;
import com.litongjava.tio.utils.jfinal.P;

@ComponentScan
public class App {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    P.use("app.properties");
    TioApplicationWrapper.run(App.class, args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "ms");
  }
}
