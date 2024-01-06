package com.litongjava.tio.web.hello.controller;

import java.util.List;

import com.litongjava.jfinal.aop.annotation.AAutowired;
import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.web.hello.model.Stable1;
import com.litongjava.tio.web.hello.service.Stable1Service;

@RequestPath("/stable1")
public class Stable1Controller {

  @AAutowired
  private Stable1Service stable1Service;

  public List<Stable1> selectList() {
    return stable1Service.selectList();
  }
}
