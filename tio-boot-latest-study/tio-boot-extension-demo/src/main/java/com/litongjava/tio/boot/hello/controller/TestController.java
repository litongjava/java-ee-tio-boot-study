package com.litongjava.tio.boot.hello.controller;

import com.litongjava.jfinal.aop.annotation.AController;
import com.litongjava.tio.http.server.annotation.RequestPath;

@AController
@RequestPath("/test")
public class TestController {

  @RequestPath
  public String index() {
    return "index";
  }
}