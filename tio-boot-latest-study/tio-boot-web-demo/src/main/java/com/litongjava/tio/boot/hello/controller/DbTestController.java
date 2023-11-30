package com.litongjava.tio.boot.hello.controller;

import java.util.List;

import org.tio.http.common.HttpRequest;
import org.tio.http.server.annotation.RequestPath;

import com.jfinal.kit.Kv;
import com.litongjava.data.model.DbJsonBean;
import com.litongjava.data.services.DbJsonService;
import com.litongjava.data.utils.DbJsonBeanUtils;
import com.litongjava.jfinal.aop.Aop;

@RequestPath("/db/student")
public class DbTestController {

  DbJsonService dbJsonService = Aop.get(DbJsonService.class);

  @RequestPath("/list")
  public DbJsonBean<List<Kv>> list(HttpRequest request) {
    String tableName = "student";
    DbJsonBean<List<Kv>> jsonBean = DbJsonBeanUtils.recordsToKv(dbJsonService.listAll(tableName));
    return jsonBean;
  }
}
