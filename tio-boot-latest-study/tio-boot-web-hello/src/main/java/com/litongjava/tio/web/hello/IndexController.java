package com.litongjava.tio.web.hello;

import com.litongjava.jfinal.aop.annotation.AController;
import com.litongjava.tio.http.server.annotation.RequestPath;

@AController
@RequestPath("/")
public class IndexController {
  @RequestPath()
  public String index() {
    return "index";
  }
}
