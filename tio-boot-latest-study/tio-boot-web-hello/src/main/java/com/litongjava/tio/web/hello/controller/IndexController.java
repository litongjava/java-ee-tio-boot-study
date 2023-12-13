package com.litongjava.tio.web.hello.controller;

import java.util.Map;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.jfinal.aop.Before;
import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.web.hello.service.IndexService;

@RequestPath("/")
public class IndexController {
  @RequestPath()
  @Before(IndexInteceptor.class)
  public Map<String, String> index() {
    return Aop.get(IndexService.class).index();
  }

  @RequestPath("classloalder")
  public String classloalder() {
    return this.getClass().getClassLoader().toString();
  }
}
