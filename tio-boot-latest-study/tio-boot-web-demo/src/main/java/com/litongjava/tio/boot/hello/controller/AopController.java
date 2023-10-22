package com.litongjava.tio.boot.hello.controller;

import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.http.server.annotation.RequestPath;
import org.tio.http.server.util.Resps;

import com.litongjava.jfinal.aop.Aop;

@RequestPath("/aop")
public class AopController {

  @RequestPath("/list")
  public HttpResponse list(HttpRequest reqeust) {
    String[] beans = Aop.beans();
    return Resps.json(reqeust, beans);
  }

}
