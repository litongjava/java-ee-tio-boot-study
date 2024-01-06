package com.litongjava.tio.web.hello.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stable1 {
  // create table stable1(ts timestamp, f1 tinyint, f2 smallint, f3 int, f4 bigint) tags(t1 tinyint, t2 smallint, t3 int, t4 bigint)
  private java.sql.Timestamp ts;
  private java.lang.Byte f1;
  private java.lang.Short f2;
  private java.lang.Integer f3;
  private java.lang.Long f4;
}
