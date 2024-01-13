package com.litongjava.tio.boot.hello.controller;

import com.litongjava.jfinal.aop.annotation.AController;
import com.litongjava.tio.http.common.Cookie;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.http.server.util.Resps;

@AController
@RequestPath("/test/cookie")
public class TestCookieController {

  @RequestPath("/index")
  public String index(HttpRequest request) {
    Cookie cookie = request.getCookie("access-token");
    if (cookie != null) {
      String accessToken = cookie.getValue();
      return accessToken;
    }
    return null;
  }

  @RequestPath("/set-cookie")
  public HttpResponse setCookie(HttpRequest request) {

    HttpResponse response = Resps.txt(request, "set-cookit");
    Cookie cookie = new Cookie(null, "access-token", "token-value", null);
    response.addCookie(cookie);
    return response;
  }

}
