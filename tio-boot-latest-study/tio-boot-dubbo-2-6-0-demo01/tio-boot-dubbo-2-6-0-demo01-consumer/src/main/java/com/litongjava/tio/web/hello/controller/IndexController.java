package com.litongjava.tio.web.hello.controller;

import com.litongjava.jfinal.dubbo.Dubbo;
import com.litongjava.tio.dubbo.demo001.service.HelloService;
import com.litongjava.tio.dubbo.demo001.service.HiService;
import com.litongjava.tio.http.server.annotation.RequestPath;

@RequestPath("/")
public class IndexController {

  @RequestPath()
  public String index() {
    HelloService helloService = Dubbo.get(HelloService.class);
    HiService hiService = Dubbo.get(HiService.class);
    return helloService.sayHello("Tong Li") + "_" + hiService.sayHi("Tong Li");
  }
}
