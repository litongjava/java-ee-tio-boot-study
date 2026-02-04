package com.demo.gateway.check;

import com.litongjava.annotation.GatewayCheck;

public class TestController {

  @GatewayCheck
  public String index() {
    return "001";
  }
}