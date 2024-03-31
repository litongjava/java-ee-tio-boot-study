package com.litongjava.tio.web.hello.controller;

import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.http.server.util.Resps;
import com.litongjava.tio.utils.resp.RespVo;

@RequestPath("/")
public class IndexController {
  @RequestPath()
  public String index() {
    return "index";
  }

  @RequestPath("customResponseCode")
  public HttpResponse customResponseCode(HttpRequest request, int code) {
    HttpResponse httpResponse = Resps.txt(request, "custom response code");
    httpResponse.setStatus(code);
    return httpResponse;
  }

  @RequestPath("json")
  public HttpResponse json(HttpRequest request) {
    RespVo fail = RespVo.fail("json");
    HttpResponse response = new HttpResponse(request);
    return response.setJson(fail);
  }

  @RequestPath("json2")
  public HttpResponse json2(HttpRequest request) {
    RespVo fail = RespVo.fail("json");
    return HttpResponse.json(request, fail);
  }

  @RequestPath("json3")
  public HttpResponse json3() {
    RespVo fail = RespVo.fail("json");
    return HttpResponse.json(fail);
  }
}
