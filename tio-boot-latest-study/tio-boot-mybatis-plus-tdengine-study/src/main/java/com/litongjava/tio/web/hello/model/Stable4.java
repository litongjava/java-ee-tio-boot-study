package com.litongjava.tio.web.hello.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stable4 {
  //create table stable4(ts timestamp, f1 binary(30)) tags(t1 binary(30))
  private java.sql.Timestamp ts;
  private java.lang.Byte[] f1;
}
