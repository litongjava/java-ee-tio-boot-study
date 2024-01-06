package com.litongjava.tio.web.hello.controller;

import java.util.List;

import com.litongjava.jfinal.aop.annotation.AAutowired;
import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.web.hello.model.Stable2;
import com.litongjava.tio.web.hello.service.Stable2Service;

@RequestPath("/stable2")
public class Stable2Controller {

  @AAutowired
  private Stable2Service stable2Service;

  public List<Stable2> selectList() {
    return stable2Service.selectList();
  }
}
