package com.litongjava.tio.web.hello;

import com.litongjava.hotswap.wrapper.tio.boot.TioApplicationWrapper;
import org.tio.utils.jfinal.P;

public class App {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    P.use("app.properties");
    TioApplicationWrapper.run(App.class, args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "ms");
  }
}
