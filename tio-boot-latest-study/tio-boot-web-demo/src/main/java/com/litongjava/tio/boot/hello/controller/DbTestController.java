package com.litongjava.tio.boot.hello.controller;

import java.util.List;

import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.http.server.annotation.RequestPath;
import org.tio.http.server.util.Resps;

import com.jfinal.plugin.activerecord.Record;
import com.litongjava.data.model.DbJsonBean;
import com.litongjava.data.services.DbJsonService;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.jfinal.aop.annotation.Controller;

@Controller
@RequestPath("/db")
public class DbTestController {

//  @Inject
//  private DbJsonService dbJsonService;
  DbJsonService dbJsonService = Aop.get(DbJsonService.class);
  
  @RequestPath("/list")
  public HttpResponse list(HttpRequest request) {
    System.out.println(dbJsonService);
    DbJsonBean<List<Record>> all = dbJsonService.listAll("cf_alarm");
    return Resps.json(request, all);
  }
}
