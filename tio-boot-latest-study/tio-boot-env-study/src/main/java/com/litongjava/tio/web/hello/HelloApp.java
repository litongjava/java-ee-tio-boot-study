package com.litongjava.tio.web.hello;

import com.litongjava.jfinal.aop.annotation.ComponentScan;
import com.litongjava.jfinal.aop.annotation.Controller;
import com.litongjava.tio.boot.TioApplication;
import com.litongjava.tio.http.server.annotation.RequestPath;

@ComponentScan
@Controller
@RequestPath("/")
public class HelloApp {
  public static void main(String[] args) {
    TioApplication.run(HelloApp.class, args);
  }

  @RequestPath()
  public String index() {
    return "index4";
  }
}
