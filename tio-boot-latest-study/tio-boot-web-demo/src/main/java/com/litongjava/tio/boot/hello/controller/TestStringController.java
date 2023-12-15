package com.litongjava.tio.boot.hello.controller;

import com.litongjava.tio.http.server.annotation.RequestPath;

@RequestPath("/test/string")
public class TestStringController {

  @RequestPath("/")
  public String index() {
    return "index";
  }
}
