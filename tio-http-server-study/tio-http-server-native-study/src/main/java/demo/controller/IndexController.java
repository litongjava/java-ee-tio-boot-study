package demo.controller;

import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.server.util.Resps;
import demo.model.Hello;
import jdk.nashorn.internal.objects.NativeJSON;

public class IndexController {

  public HttpResponse index(HttpRequest request) {
    return Resps.txt(request, "index");

  }

  public HttpResponse txt(HttpRequest request) {
    return Resps.txt(request, "index");
  }

  public HttpResponse exception(HttpRequest request) {
    throw new RuntimeException("error");
  }

  public HttpResponse json(HttpRequest httpRequest) {
    Hello hello = new Hello("Tong Li", "Earth");
    return Resps.json(httpRequest, hello);
  }
}
