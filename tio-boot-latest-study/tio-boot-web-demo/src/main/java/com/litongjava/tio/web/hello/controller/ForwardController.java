package com.litongjava.tio.web.hello.controller;

import com.litongjava.annotation.Get;
import com.litongjava.annotation.RequestPath;
import com.litongjava.tio.boot.http.TioRequestContext;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;

@RequestPath("/forward")
public class ForwardController {

  @Get
  public HttpResponse forward() {
    HttpRequest request = TioRequestContext.getRequest();
    HttpResponse forward = null;
    try {
      forward = request.forward("/");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return forward;
  }
}
