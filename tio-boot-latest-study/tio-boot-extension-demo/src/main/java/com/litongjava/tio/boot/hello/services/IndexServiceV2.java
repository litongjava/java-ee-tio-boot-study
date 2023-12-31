package com.litongjava.tio.boot.hello.services;

import com.litongjava.jfinal.aop.Autowired;
import com.litongjava.jfinal.aop.annotation.Service;
import com.litongjava.tio.boot.hello.model.User;

@Service
public class IndexServiceV2 implements IndexService {

  @Autowired
  private UserService userService;

  public User getUser() {
    return userService.getUser();
  }
}