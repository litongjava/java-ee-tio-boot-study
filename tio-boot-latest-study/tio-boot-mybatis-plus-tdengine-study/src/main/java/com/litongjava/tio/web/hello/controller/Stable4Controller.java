package com.litongjava.tio.web.hello.controller;

import java.util.List;

import com.litongjava.jfinal.aop.annotation.AAutowired;
import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.web.hello.model.Stable4;
import com.litongjava.tio.web.hello.service.Stable4Service;

@RequestPath("/stable4")
public class Stable4Controller {

  @AAutowired
  private Stable4Service stable4Service;

  public List<Stable4> selectList() {
    return stable4Service.selectList();
  }
}
