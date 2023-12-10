package com.litongjava.tio.boot.djl.controller;

import org.tio.http.server.annotation.RequestPath;

@RequestPath("/")
public class IndexController {

  @RequestPath("")
  public String index() {
    return "index0";
  }
}
