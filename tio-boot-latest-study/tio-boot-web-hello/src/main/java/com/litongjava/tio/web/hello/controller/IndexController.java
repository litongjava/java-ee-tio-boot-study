package com.litongjava.tio.web.hello.controller;

import org.tio.http.server.annotation.RequestPath;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.tio.web.hello.service.IndexService;

@RequestPath("")
public class IndexController {
  @RequestPath("")
  public String index() {
    return Aop.get(IndexService.class).index();
  }
}
