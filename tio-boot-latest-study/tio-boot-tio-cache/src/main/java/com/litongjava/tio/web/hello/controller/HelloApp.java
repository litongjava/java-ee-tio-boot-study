package com.litongjava.tio.web.hello.controller;

import com.litongjava.tio.http.server.annotation.RequestPath;

@RequestPath("/")
public class HelloApp {
  @RequestPath()
  public String index() {
    return "index";
  }
}
