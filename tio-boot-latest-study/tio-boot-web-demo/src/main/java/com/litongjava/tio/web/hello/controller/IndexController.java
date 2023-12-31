package com.litongjava.tio.web.hello.controller;

import com.litongjava.jfinal.aop.annotation.AComponentScan;
import com.litongjava.jfinal.aop.annotation.AController;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.http.server.util.Resps;

@AComponentScan
@AController
@RequestPath("/")
public class IndexController {
  @RequestPath()
  public String index() {
    return "index";
  }
  
  @RequestPath("customResponseCode")
  public HttpResponse customResponseCode(HttpRequest request,int code) {
    HttpResponse httpResponse = Resps.txt(request, "custom response code");
    httpResponse.setStatus(code);
    return httpResponse;
  }
}

