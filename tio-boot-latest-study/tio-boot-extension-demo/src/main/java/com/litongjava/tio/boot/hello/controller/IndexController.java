package com.litongjava.tio.boot.hello.controller;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.jfinal.aop.annotation.AAutowired;
import com.litongjava.tio.boot.context.TioApplicationContext;
import com.litongjava.tio.boot.hello.services.IndexService;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.http.server.util.Resps;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestPath("/")
public class IndexController {

//  private IndexService indexService = Aop.get(IndexService.class);
  // 不支持在Controller中使用@Autowired注解
  @AAutowired
  private IndexService indexService;

  @RequestPath
  public HttpResponse respText(HttpRequest request) throws Exception {
    log.info("txt");
    HttpResponse ret = Resps.txt(request, "hello 1");
    return ret;
  }

  @RequestPath("get")
  public HttpResponse json(HttpRequest request) {
    return Resps.json(request, indexService.getUser());
  }

  @RequestPath("isRunning")
  public HttpResponse isRunning(HttpRequest request) {
    TioApplicationContext tioApplicationContext = Aop.get(TioApplicationContext.class);
    boolean running = tioApplicationContext.isRunning();
    return Resps.json(request, running);
  }

  @RequestPath("close")
  public HttpResponse close(HttpRequest request) {
    TioApplicationContext tioApplicationContext = Aop.get(TioApplicationContext.class);
    tioApplicationContext.close();
    return Resps.json(request, "OK");
  }
}