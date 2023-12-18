package com.litongjava.tio.boot.hello.config;

import com.litongjava.jfinal.aop.Autowired;
import com.litongjava.jfinal.aop.annotation.Configuration;
import com.litongjava.jfinal.aop.annotation.Initialization;
import com.litongjava.tio.boot.hello.model.User;
import com.litongjava.tio.boot.hello.services.IndexService;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class ServiceAutowiredConfig {

  @Autowired
  private IndexService indexService;

  @Initialization
  public void nothingToDo() {
    User user = indexService.getUser();
    log.info("user:{}", user);
  }
}
