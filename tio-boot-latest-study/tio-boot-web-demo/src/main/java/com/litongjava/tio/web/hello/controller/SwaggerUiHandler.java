package com.litongjava.tio.web.hello.controller;

import java.net.URL;

import com.litongjava.tio.boot.http.TioRequestContext;
import com.litongjava.tio.boot.utils.HttpBasicAuthUtils;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.server.util.Resps;
import com.litongjava.tio.utils.hutool.FileUtil;
import com.litongjava.tio.utils.hutool.ResourceUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SwaggerUiHandler {

  public HttpResponse html(HttpRequest request) throws Exception {
    String authorization = request.getHeader("authorization");
    if (!HttpBasicAuthUtils.authenticate(authorization, "admin", "admin")) {
      HttpResponse response = TioRequestContext.getResponse();
      response.setStatus(401);
      response.addHeader("WWW-Authenticate", "Basic realm=\"Access to the site\"");
      return response;
    }
    // 获取 META-INF 目录下的 doc.html 文件内容
    String path = "META-INF/resources/doc.html";
    URL resource = ResourceUtil.getResource(path);
    HttpResponse response = TioRequestContext.getResponse();

    String html = null;
    if (resource == null) {
      log.error("resouce is empty:{}", path);
      html = "resouce is empty";
    } else {
      html = FileUtil.readString(resource).toString();
    }
    return Resps.html(response, html);
  }
}
