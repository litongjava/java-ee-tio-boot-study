package com.litongjava.tio.web.hello.config;

import com.litongjava.annotation.AConfiguration;
import com.litongjava.annotation.Initialization;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.web.hello.aspect.MyGatewayCheckAspect;
import com.litongjava.tio.web.hello.aspect.MyRequiresAuthenticationAspect;
import com.litongjava.tio.web.hello.aspect.MyRequiresPermissionsAspect;

@AConfiguration
public class TioBootServerConfig {

  @Initialization
  public void config() {
    TioBootServer server = TioBootServer.me();
    server.setGateWayCheckAspect(new MyGatewayCheckAspect());
    server.setRequiresAuthenticationAspect(new MyRequiresAuthenticationAspect());
    server.setRequiresPermissionsAspect(new MyRequiresPermissionsAspect());
  }

}
