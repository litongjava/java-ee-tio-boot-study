package com.litongjava.tio.web.hello.controller;

import com.litongjava.annotation.RequestPath;
import com.litongjava.aop.GatewayCheck;
import com.litongjava.aop.RequiresAuthentication;
import com.litongjava.aop.RequiresPermissions;
import com.litongjava.model.body.RespBodyVo;

@RequestPath("/check")
public class CheckController {
  @GatewayCheck
  public RespBodyVo gatewary() {
    return RespBodyVo.ok();
  }

  @RequiresAuthentication
  public RespBodyVo requiresAuthentication() {
    return RespBodyVo.ok();
  }

  @RequiresPermissions("test")
  public RespBodyVo requiresPermissions() {
    return RespBodyVo.ok();
  }
}
