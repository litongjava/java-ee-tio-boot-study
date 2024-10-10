package com.litongjava.tio.web.hello.aspect;

import java.lang.reflect.Method;

import com.litongjava.tio.boot.aspect.IRequiresAuthenticationAspect;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyRequiresAuthenticationAspect implements IRequiresAuthenticationAspect {

  @Override
  public HttpResponse check(HttpRequest request, Object targetController, Method actionMethod) {
    log.info("actionMethod:{}", actionMethod);
    return null;
  }

}
