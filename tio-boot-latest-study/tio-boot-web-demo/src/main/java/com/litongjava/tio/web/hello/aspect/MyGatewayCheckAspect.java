package com.litongjava.tio.web.hello.aspect;

import java.lang.reflect.Method;

import com.litongjava.aop.GatewayCheck;
import com.litongjava.tio.boot.aspect.IGateWayCheckAspect;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyGatewayCheckAspect implements IGateWayCheckAspect {

  @Override
  public HttpResponse check(HttpRequest request, Object targetController, Method actionMethod, GatewayCheck annotation) {
    log.info(annotation.toString());
    return null;
  }

}
