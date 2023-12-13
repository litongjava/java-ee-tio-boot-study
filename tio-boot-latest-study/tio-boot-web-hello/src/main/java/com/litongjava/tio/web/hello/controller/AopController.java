package com.litongjava.tio.web.hello.controller;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.tio.http.server.annotation.RequestPath;

@RequestPath("/aop")
public class AopController {

  @RequestPath("/beans")
  public String[] beans() {
    String[] beans = Aop.beans();
    return beans;
  }
}
