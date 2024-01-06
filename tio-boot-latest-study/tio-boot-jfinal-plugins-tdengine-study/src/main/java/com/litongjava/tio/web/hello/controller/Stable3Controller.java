package com.litongjava.tio.web.hello.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.litongjava.jfinal.plugin.activerecord.Db;
import com.litongjava.jfinal.plugin.activerecord.Record;
import com.litongjava.tio.http.server.annotation.RequestPath;

@RequestPath("/stable3")
public class Stable3Controller {

  public List<Map<String, Object>> list() {
    List<Record> records = Db.findAll("stable3");
    List<Map<String, Object>> result = records.stream().map((t) -> t.toMap()).collect(Collectors.toList());
    return result;
  }
}
