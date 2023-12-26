package com.litongjava.tio.web.hello;

import org.tio.utils.jfinal.P;

import com.litongjava.hotswap.debug.Diagnostic;
import com.litongjava.hotswap.wrapper.tio.boot.TioApplicationWrapper;
import com.litongjava.jfinal.aop.annotation.ComponentScan;

@ComponentScan
public class App {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    P.use("app.properties");
    Diagnostic.setDebug(true);
    TioApplicationWrapper.run(App.class, args);
//    TioApplication.run(App.class, args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "ms");
  }
}
