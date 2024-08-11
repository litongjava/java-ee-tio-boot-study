package com.litongjava.tio.dubbo.demo001.service.impl;

import com.litongjava.jfinal.aop.annotation.AService;
import com.litongjava.tio.dubbo.demo001.service.HelloService;

@AService
public class HelloServiceImpl implements HelloService {
  public String sayHello(String name) {
    return "Hello " + name;
  }
}
