package com.litongjava.tio.web.hello.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stable3 {
  // create table stable3(ts timestamp, f1 bool) tags(t1 bool)
  private java.sql.Timestamp ts;
  private java.lang.Boolean f1;
}
