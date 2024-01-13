package com.litongjava.tio.boot.hello.controller;

import com.litongjava.jfinal.aop.annotation.AController;
import com.litongjava.tio.http.server.annotation.RequestPath;

@AController
@RequestPath("/test/string")
public class TestStringController {

  @RequestPath("/")
  public String index() {
    return "index";
  }
}
