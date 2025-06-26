package com.litongjava.tio.boot.postgresql.demo.controller;
import java.util.List;

import com.jfinal.kit.Kv;
import com.litongjava.annotation.RequestPath;
import com.litongjava.db.TableResult;
import com.litongjava.db.activerecord.Row;
import com.litongjava.table.services.ApiTable;
import com.litongjava.table.utils.TableResultUtils;
import com.litongjava.tio.http.common.HttpRequest;

@RequestPath("/db/student")
public class DbTestController {


  @RequestPath("/list")
  public TableResult<List<Kv>> list(HttpRequest request) {
    String tableName = "student";
    TableResult<List<Row>> result = ApiTable.listAll(tableName);
    TableResult<List<Kv>> recordsToKv = TableResultUtils.recordsToKv(result, false);
    return recordsToKv;
  }
}