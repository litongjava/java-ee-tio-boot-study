package com.litongjava.tio.web.hello.controller;

import java.util.List;

import com.litongjava.jfinal.aop.annotation.AAutowired;
import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.web.hello.model.Stable5;
import com.litongjava.tio.web.hello.service.Stable5Service;

@RequestPath("/stable5")
public class Stable5Controller {

  @AAutowired
  private Stable5Service stable5Service;

  public List<Stable5> selectList() {
    return stable5Service.selectList();
  }
}
