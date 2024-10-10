package com.demo.gateway.check;

import com.litongjava.aop.GatewayCheck;

public class TestController {

  @GatewayCheck
  public String index() {
    return "001";
  }
}
