package com.litongjava.tio.boot.hello.controller;

import org.tio.http.server.annotation.RequestPath;

import com.litongjava.data.model.DbJsonBean;

import cn.hutool.core.lang.Validator;

@RequestPath("/login")
public class LoginController {

  @RequestPath()
  public DbJsonBean<String> login(String username, String password) {
    DbJsonBean<String> dbJsonBean = new DbJsonBean<>();
    if (Validator.isNull(username)) {
      dbJsonBean.setCode(-1);
      dbJsonBean.setMsg("usernname can't be null");
      return dbJsonBean;
    }
    return dbJsonBean;
  }

}
