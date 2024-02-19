package com.litongjava.tio.boot.sqllite.controller;

import java.util.List;

import com.litongjava.data.model.DbJsonBean;
import com.litongjava.data.services.DbJsonService;
import com.litongjava.jfinal.aop.annotation.AAutowired;
import com.litongjava.jfinal.plugin.activerecord.Record;
import com.litongjava.tio.http.server.annotation.RequestPath;

@RequestPath()
public class TestController {

  @AAutowired
  private DbJsonService dbJsonService;

  @RequestPath("/list")
  public DbJsonBean<List<Record>> list() {
    DbJsonBean<List<Record>> listAll = dbJsonService.listAll("student");
    return listAll;
  }
}
