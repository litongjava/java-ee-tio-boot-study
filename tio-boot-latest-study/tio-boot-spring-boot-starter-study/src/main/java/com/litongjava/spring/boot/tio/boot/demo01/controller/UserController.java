package com.litongjava.spring.boot.tio.boot.demo01.controller;

import java.util.List;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.jfinal.plugin.activerecord.Record;
import com.litongjava.spring.boot.tio.boot.demo01.service.UserService;
import com.litongjava.tio.http.server.annotation.RequestPath;

@RequestPath("/user")
public class UserController {

  public List<Record> listAll() {
    return Aop.get(UserService.class).listAll();
  }
}
