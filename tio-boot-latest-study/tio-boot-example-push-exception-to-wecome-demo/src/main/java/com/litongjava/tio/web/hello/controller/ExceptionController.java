package com.litongjava.tio.web.hello.controller;

import com.litongjava.tio.http.server.annotation.RequestPath;

@RequestPath("/exception")
public class ExceptionController {

  @RequestPath()
  public Integer index() {
    return (0 / 0);
  }

}
