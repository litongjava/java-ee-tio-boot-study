package com.demo.thymeleaf.controller;

import org.thymeleaf.context.Context;

import com.litongjava.tio.boot.http.TioRequestContext;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.http.server.util.Resps;
import com.litongjava.tio.utils.thymeleaf.Thymyleaf;

@RequestPath("/hello")
public class HelloController {

  public HttpResponse index() {
    HttpResponse response = TioRequestContext.getResponse();
    // 创建 Thymeleaf 上下文并设置变量
    Context context = new Context();
    context.setVariable("name", "World");

    // 渲染模板
    String html = Thymyleaf.process("hello", context);

    // 返回渲染后的 HTML
    return Resps.html(response, html);
  }
}
