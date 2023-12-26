package demo.controller;

import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.server.util.Resps;

public class IndexController {

  public HttpResponse index(HttpRequest request) {
    return Resps.txt(request, "index");

  }

  public HttpResponse login(HttpRequest request) {
    return Resps.txt(request, "login");
  }

  public HttpResponse exception(HttpRequest request) {
    throw new RuntimeException("error");
  }
}
