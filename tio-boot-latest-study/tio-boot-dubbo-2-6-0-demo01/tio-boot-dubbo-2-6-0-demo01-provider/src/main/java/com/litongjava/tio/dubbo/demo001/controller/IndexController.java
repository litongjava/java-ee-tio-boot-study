package com.litongjava.tio.dubbo.demo001.controller;

import com.litongjava.jfinal.aop.annotation.AAutowired;
import com.litongjava.tio.dubbo.demo001.service.HelloService;
import com.litongjava.tio.http.server.annotation.RequestPath;

@RequestPath("/")
public class IndexController {

  @AAutowired
  private HelloService helloService;

  @RequestPath()
  public String index() {
    return helloService.sayHello("Tong Li");
  }
}
