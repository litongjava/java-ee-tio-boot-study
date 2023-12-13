package com.litongjava.tio.web.hello.controller;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.utils.resp.RespVo;
import com.litongjava.tio.web.hello.validator.LoginValidator;

@RequestPath("/auth")
public class LoginController {

  public RespVo login(String username, String password, String verificationCode) {

    RespVo validateResult = Aop.get(LoginValidator.class).validateLogin(username, password, verificationCode);
    if (validateResult != null) return validateResult;

    return RespVo.ok();
  }
}
