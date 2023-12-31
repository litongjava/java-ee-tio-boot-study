package com.litongjava.tio.boot.hello.controller;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.jfinal.aop.annotation.Controller;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.http.server.util.Resps;

@Controller
@RequestPath("/aop")
public class AopController {

  @RequestPath("/list")
  public HttpResponse list(HttpRequest reqeust) {
    String[] beans = Aop.beans();
    return Resps.json(reqeust, beans);
  }

}
