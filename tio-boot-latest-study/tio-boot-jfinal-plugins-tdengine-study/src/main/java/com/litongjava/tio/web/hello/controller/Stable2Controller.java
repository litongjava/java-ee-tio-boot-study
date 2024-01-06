package com.litongjava.tio.web.hello.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.litongjava.jfinal.plugin.activerecord.Db;
import com.litongjava.jfinal.plugin.activerecord.Record;
import com.litongjava.tio.http.server.annotation.RequestPath;

@RequestPath("/stable2")
public class Stable2Controller {

  public List<Map<String, Object>> list() {
    List<Record> records = Db.findAll("stable2");
    List<Map<String, Object>> result = records.stream().map((t) -> t.toMap()).collect(Collectors.toList());
    return result;
  }
}
