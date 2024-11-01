package com.litongjava.tio.web.hello.handler;

import java.util.HashMap;
import java.util.Map;

import com.litongjava.model.body.RespBodyVo;
import com.litongjava.tio.boot.http.TioRequestContext;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.web.hello.utils.HttpBasicAuthUtils;

public class HelloHandler {

  public HttpResponse hello(HttpRequest request) {
    // 执行认证
    String authorization = request.getHeader("authorization");
    if (!HttpBasicAuthUtils.authenticate(authorization, "admin", "admin")) {
      HttpResponse response = TioRequestContext.getResponse();
      response.setStatus(401);
      response.addHeader("WWW-Authenticate", "Basic realm=\"Access to the site\"");
      return response;
    }

    // 认证通过，返回响应数据
    Map<String, String> data = new HashMap<>();
    RespBodyVo respVo = RespBodyVo.ok(data);
    return TioRequestContext.getResponse().setJson(respVo);
  }
}
