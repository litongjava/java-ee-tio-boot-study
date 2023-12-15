package com.litongjava.tio.boot.hello.controller;

import com.litongjava.tio.boot.hello.model.User;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.http.server.util.Resps;
import com.litongjava.tio.utils.resp.Resp;
import com.litongjava.tio.utils.resp.RespVo;

import lombok.extern.slf4j.Slf4j;

@RequestPath("/test/json")
@Slf4j
public class TestJsonController {
  
  @RequestPath("/responseJsonRespVo")
  public HttpResponse responseJsonResps(HttpRequest request) {
    User user = User.builder().loginName("Ping E Lee").nick("李通").ip("127.0.0.1").build();
    return Resps.json(request, RespVo.ok(user));
  }
  
  @RequestPath("/responseJsonResp")
  public HttpResponse responseJsonResp(HttpRequest request) {
    User user = User.builder().loginName("Ping E Lee").nick("李通").ip("127.0.0.1").build();
    return Resps.json(request, Resp.ok(user));
  }
  
  @RequestPath(value = "/responseJson")
  public HttpResponse json(HttpRequest request) throws Exception {
    User user = User.builder().loginName("Ping E Lee").nick("李通").ip("127.0.0.1").build();
    HttpResponse ret = Resps.json(request, user);
    return ret;
  }
  
  @RequestPath(value = "/responseUser")
  public User responseUser(HttpRequest request) throws Exception {
    return User.builder().loginName("Ping E Lee").nick("李通").ip("127.0.0.1").build();
  }

  @RequestPath(value = "/getBodyString")
  public HttpResponse getBodyString(HttpRequest request) throws Exception {
    String bodyString = request.getBodyString();
    log.info(bodyString);
    HttpResponse ret = Resps.txt(request, bodyString);
    return ret;
  }
  
  @RequestPath(value = "/bean")
  public User bean(User user, HttpRequest request) throws Exception {
    return user;
  }

}
