package com.litongjava.tio.http.server.controller;

import java.util.HashMap;
import java.util.Map;

import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.server.util.Resps;

public class IndexController {

  public HttpResponse index(HttpRequest request) {
    return Resps.txt(request, "tio-server");
  }

  public HttpResponse plaintext(HttpRequest request) {
    return Resps.txt(request, "Hello, World!");
  }

  // 在IndexController中添加
  public HttpResponse json(HttpRequest request) {
    // 示例对象
    Map<String, Object> data = new HashMap<>();
    data.put("message", "Hello, World!");
    return Resps.json(request, data); // 使用Resps.json返回JSON响应
  }
}
