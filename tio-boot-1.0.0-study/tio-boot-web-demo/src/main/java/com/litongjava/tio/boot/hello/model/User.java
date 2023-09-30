package com.litongjava.tio.boot.hello.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {
  private String loginName,nick,ip;

}
