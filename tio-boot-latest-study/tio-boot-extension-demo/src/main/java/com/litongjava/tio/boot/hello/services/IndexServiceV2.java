package com.litongjava.tio.boot.hello.services;

import com.litongjava.jfinal.aop.annotation.AAutowired;
import com.litongjava.jfinal.aop.annotation.AService;
import com.litongjava.tio.boot.hello.model.User;

@AService
public class IndexServiceV2 implements IndexService {

  @AAutowired
  private UserService userService;

  public User getUser() {
    return userService.getUser();
  }
}