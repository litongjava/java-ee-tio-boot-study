package com.litongjava.tio.boot.hello.config;

import com.litongjava.jfinal.aop.annotation.AAutowired;
import com.litongjava.jfinal.aop.annotation.AConfiguration;
import com.litongjava.jfinal.aop.annotation.AInitialization;
import com.litongjava.tio.boot.hello.model.User;
import com.litongjava.tio.boot.hello.services.IndexService;

import lombok.extern.slf4j.Slf4j;

@AConfiguration
@Slf4j
public class ServiceAutowiredConfig {

  @AAutowired
  private IndexService indexService;

  @AInitialization
  public void nothingToDo() {
    User user = indexService.getUser();
    log.info("user:{}", user);
  }
}
