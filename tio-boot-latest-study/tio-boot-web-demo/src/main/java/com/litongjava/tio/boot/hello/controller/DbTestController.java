package com.litongjava.tio.boot.hello.controller;

import java.util.List;

import com.jfinal.kit.Kv;
import com.litongjava.data.model.DbJsonBean;
import com.litongjava.data.services.DbJsonService;
import com.litongjava.data.utils.DbJsonBeanUtils;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.server.annotation.RequestPath;

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
