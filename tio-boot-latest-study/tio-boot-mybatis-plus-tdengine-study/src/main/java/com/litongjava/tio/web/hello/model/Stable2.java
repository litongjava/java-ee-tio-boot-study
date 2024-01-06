package com.litongjava.tio.web.hello.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stable2 {
  // create table stable2(ts timestamp, f1 float, f2 double) tags(t1 float, t2 double)
  private java.sql.Timestamp ts;
  private java.lang.Float f1;
  private java.lang.Double f2;
}
