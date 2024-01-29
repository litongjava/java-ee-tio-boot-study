package com.litongjava.tio.web.hello;

import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.server.util.Resps;

public class HelloController {

  public HttpResponse hello(HttpRequest httpRequest) {
    return Resps.txt(httpRequest, "hello");
  }

  public HttpResponse hi(HttpRequest httpRequest) {
    return Resps.txt(httpRequest, "hi");
  }
}
