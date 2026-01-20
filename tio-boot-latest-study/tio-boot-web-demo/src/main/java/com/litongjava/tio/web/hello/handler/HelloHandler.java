package com.litongjava.tio.web.hello.handler;

import java.util.HashMap;
import java.util.Map;

import com.litongjava.model.body.RespBodyVo;
import com.litongjava.tio.boot.http.TioRequestContext;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.server.handler.HttpRequestHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloHandler implements HttpRequestHandler {
  @Override
  public HttpResponse handle(HttpRequest httpRequest) throws Exception {
    log.info("id:{}",httpRequest.getId());
    // 认证通过，返回响应数据
    Map<String, String> data = new HashMap<>();
    RespBodyVo respVo = RespBodyVo.ok(data);
    return TioRequestContext.getResponse().setJson(respVo);
  }
}
