package com.litongjava.quarkus.handler;

import com.litongjava.quarkus.service.UserService;
import com.litongjava.tio.boot.http.TioRequestContext;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;

import io.quarkus.arc.Unremovable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
@Unremovable
public class UserHandler {

  @Inject
  UserService userService;

  public HttpResponse createUser(HttpRequest httpRequest) throws Exception {
    String username = httpRequest.getParam("username");
    String password = httpRequest.getParam("password");
    return TioRequestContext.getResponse().setJson(userService.createUser(username, password));
  }

  public HttpResponse getUser(HttpRequest httpRequest) throws Exception {
    Long id = httpRequest.getLong("id");
    return TioRequestContext.getResponse().setJson(userService.getUser(id));
  }
}