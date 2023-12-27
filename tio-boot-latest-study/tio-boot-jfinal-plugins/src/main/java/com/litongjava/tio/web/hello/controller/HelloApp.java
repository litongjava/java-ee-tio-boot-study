package com.litongjava.tio.web.hello.controller;

import com.litongjava.jfinal.aop.annotation.Controller;
import com.litongjava.tio.http.server.annotation.RequestPath;

@Controller
@RequestPath("/")
public class HelloApp {
  @RequestPath()
  public String index() {
    return "index";
  }
}
