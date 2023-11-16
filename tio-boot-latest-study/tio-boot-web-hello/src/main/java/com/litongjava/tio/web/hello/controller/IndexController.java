package com.litongjava.tio.web.hello.controller;

import java.util.Map;

import org.tio.http.server.annotation.RequestPath;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.tio.web.hello.service.IndexService;

@RequestPath("/")
public class IndexController {
  @RequestPath("index")
  public Map<String,String> index() {
    return Aop.get(IndexService.class).index();
  }
}
