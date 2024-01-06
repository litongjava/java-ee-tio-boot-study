package com.litongjava.tio.web.hello.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stable5 {
  // create table stable5(ts timestamp, f1 nchar(30)) tags(t1 nchar(30))
  private java.sql.Timestamp ts;
  private java.lang.String f1;
}
