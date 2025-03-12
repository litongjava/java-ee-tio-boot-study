package com.litongjava.test.controller;

import com.jfinal.kit.Kv;
import com.litongjava.annotation.RequestPath;
import com.litongjava.template.EnjoyTemplate;
import com.litongjava.tio.boot.http.TioRequestContext;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.server.util.Resps;

@RequestPath("/")
public class IndexController {
  @RequestPath()
  public HttpResponse index() {
    HttpResponse response = TioRequestContext.getResponse();
    String content = EnjoyTemplate.renderToString("/index.html", Kv.by("url", "http://www.baidu.com"));
    return Resps.html(response, content);
  }
}
