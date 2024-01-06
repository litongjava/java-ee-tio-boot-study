package com.litongjava.tio.web.hello.config.utils;

import javax.sql.DataSource;

public class TDUtils {

  public static DataSource ds;

  public static void setDataSource(DataSource ds) {
    TDUtils.ds = ds;
  }
}
