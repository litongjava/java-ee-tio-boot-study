package com.litongjava.tio.web.hello;

import com.litongjava.tio.http.server.annotation.RequestPath;

@RequestPath("/")
public class IndexController {
  @RequestPath()
  public String index() {
    return "index";
  }
}
