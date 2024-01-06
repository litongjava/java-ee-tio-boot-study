package com.litongjava.tio.web.hello.controller;

import java.util.List;

import com.litongjava.jfinal.aop.annotation.AAutowired;
import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.web.hello.model.Stable3;
import com.litongjava.tio.web.hello.service.Stable3Service;

@RequestPath("/stable3")
public class Stable3Controller {

  @AAutowired
  private Stable3Service stable3Service;

  public List<Stable3> selectList() {
    return stable3Service.selectList();
  }
}
