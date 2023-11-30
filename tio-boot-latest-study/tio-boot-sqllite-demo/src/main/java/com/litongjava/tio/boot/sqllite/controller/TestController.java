package com.litongjava.tio.boot.sqllite.controller;

import java.util.List;

import org.tio.http.server.annotation.RequestPath;

import com.jfinal.plugin.activerecord.Record;
import com.litongjava.data.model.DbJsonBean;
import com.litongjava.data.services.DbJsonService;
import com.litongjava.jfinal.aop.Aop;

@RequestPath("")
public class TestController {

  @RequestPath("/")
  public String index() {
    return "index";
  }

  @RequestPath("/beans")
  public String[] beans() {
    return Aop.beans();
  }

  @RequestPath("/list")
  public DbJsonBean<List<Record>> list() {
    DbJsonBean<List<Record>> listAll = Aop.get(DbJsonService.class).listAll("student");
    return listAll;
  }

}
