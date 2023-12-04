package com.litongjava.tio.web.socket.hello;

import org.tio.utils.jfinal.P;

import com.litongjava.jfinal.aop.annotation.ComponentScan;
import com.litongjava.tio.boot.TioApplication;

@ComponentScan
public class App {

  public static void main(String[] args) {
    P.use("app.properties");
    TioApplication.run(App.class, args);
  }
}
