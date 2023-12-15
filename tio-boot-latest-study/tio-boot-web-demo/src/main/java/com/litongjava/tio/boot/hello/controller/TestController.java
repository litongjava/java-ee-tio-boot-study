package com.litongjava.tio.boot.hello.controller;

import com.litongjava.tio.http.server.annotation.RequestPath;

@RequestPath("/test")
public class TestController {

  @RequestPath
  public String index() {
    return "index";
  }
}