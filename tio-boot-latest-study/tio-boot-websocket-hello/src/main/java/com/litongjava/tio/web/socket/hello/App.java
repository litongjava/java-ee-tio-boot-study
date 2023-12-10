package com.litongjava.tio.web.socket.hello;

import com.litongjava.jfinal.aop.annotation.ComponentScan;
import com.litongjava.tio.boot.TioApplication;
import com.litongjava.tio.utils.jfinal.P;

@ComponentScan
public class App {

  public static void main(String[] args) {
    P.use("app.properties");
    TioApplication.run(App.class, args);
  }
}
