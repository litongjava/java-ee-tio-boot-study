package com.litongjava.tio.boot.hello.controller;

import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.http.server.annotation.RequestPath;
import org.tio.http.server.util.Resps;

import com.litongjava.jfinal.aop.Autowired;
import com.litongjava.tio.boot.hello.services.IndexService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestPath("/")
//@Controller
public class IndexController {

//  private IndexService indexService = Aop.get(IndexService.class);
  //不支持在Controller中使用@Autowired注解
  @Autowired
  private IndexService indexService;

  @RequestPath
  public HttpResponse respText(HttpRequest request) throws Exception {
    log.info("txt");
    HttpResponse ret = Resps.txt(request, "hello");
    return ret;
  }

  @RequestPath("get")
  public HttpResponse json(HttpRequest request) {
    return Resps.json(request, indexService.getUser());
  }
}