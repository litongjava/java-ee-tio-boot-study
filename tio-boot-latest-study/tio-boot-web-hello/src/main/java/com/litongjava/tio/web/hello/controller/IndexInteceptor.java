package com.litongjava.tio.web.hello.controller;

import com.litongjava.jfinal.aop.Interceptor;
import com.litongjava.jfinal.aop.Invocation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IndexInteceptor implements Interceptor {

  @Override
  public void intercept(Invocation inv) {
    log.info("before");
    inv.invoke();
    log.info("after");

  }

}
