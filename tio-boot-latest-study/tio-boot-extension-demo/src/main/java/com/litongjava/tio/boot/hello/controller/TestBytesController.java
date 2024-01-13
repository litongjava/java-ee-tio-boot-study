package com.litongjava.tio.boot.hello.controller;

import com.litongjava.jfinal.aop.annotation.AController;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.server.annotation.RequestPath;

@AController
@RequestPath("/test/bytes")
public class TestBytesController {

  @RequestPath
  public String index(HttpRequest reuqest) {
    byte[] body = reuqest.getBody();
    return "index";
  }
}
