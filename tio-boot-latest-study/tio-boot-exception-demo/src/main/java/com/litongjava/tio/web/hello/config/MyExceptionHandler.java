package com.litongjava.tio.web.hello.config;

import com.litongjava.tio.boot.exception.TioBootExceptionHandler;
import com.litongjava.tio.http.common.HttpRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyExceptionHandler implements TioBootExceptionHandler {

  @Override
  public void handler(HttpRequest request, Throwable e) {
    String requestURI = request.getRequestURI();
    log.error("{},{}", requestURI, e);
  }

}
