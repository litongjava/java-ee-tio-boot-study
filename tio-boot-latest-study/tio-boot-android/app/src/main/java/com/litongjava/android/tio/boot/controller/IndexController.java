package com.litongjava.android.tio.boot.controller;


import com.litongjava.annotation.RequestPath;
import com.litongjava.tio.boot.http.TioRequestContext;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestPath
public class IndexController {
  private Logger log = LoggerFactory.getLogger(this.getClass());

  @RequestPath
  public String index(HttpRequest request) {
    HttpResponse response = TioRequestContext.getResponse();
    log.info("request:{}", request);
    log.info("response:{}", response);
    return "index";
  }

  public StudentReqVo student(StudentReqVo reqVo) {
    return reqVo;
  }
}
