package com.litongjava.quarkus.handler;

import com.litongjava.quarkus.service.MyService;
import com.litongjava.tio.boot.http.TioRequestContext;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.server.handler.HttpRequestHandler;

import io.quarkus.arc.Unremovable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
@Unremovable
public class HelloHandler implements HttpRequestHandler {

  @Inject
  MyService myService;

  @Override
  public HttpResponse handle(HttpRequest httpRequest) throws Exception {
    return TioRequestContext.getResponse().setJson(myService.work());
  }
}
