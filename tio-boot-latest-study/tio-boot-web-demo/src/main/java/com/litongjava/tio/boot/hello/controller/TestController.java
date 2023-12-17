package com.litongjava.tio.boot.hello.controller;

import com.litongjava.jfinal.aop.annotation.Controller;
import com.litongjava.tio.http.server.annotation.RequestPath;

@Controller
@RequestPath("/test")
public class TestController {

  @RequestPath
  public String index() {
    return "index";
  }
}