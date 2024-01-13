package com.litongjava.tio.web.hello;

import com.litongjava.tio.boot.http.TioControllerContext;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.http.server.util.Resps;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;

@RequestPath("/auth")
@Slf4j
public class AuthController {

  public Object doLogin() {
    StpUtil.login("litong");
    HttpResponse response = TioControllerContext.getResponse();
    log.info("response", response);
    return response;
  }

  public HttpResponse logout() {
    StpUtil.logout();
    SaResult ok = SaResult.ok("ok");
    HttpResponse response = TioControllerContext.getResponse();
    return Resps.json(response, ok);
  }

  public boolean validateToken() {
    try {
      StpUtil.checkActiveTimeout();
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
