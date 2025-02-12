package com.litongjava.tio.web.hello.controller;

import com.litongjava.annotation.Get;
import com.litongjava.annotation.RequestPath;
import com.litongjava.tio.boot.http.TioRequestContext;
import com.litongjava.tio.http.common.HttpResponse;

@RequestPath("/redirect")
public class RedirectController {

  @Get
  public HttpResponse index() {
    HttpResponse response = TioRequestContext.getResponse();
    response.sendRedirect("http://www.baidu.com");
    return response;
  }
}
