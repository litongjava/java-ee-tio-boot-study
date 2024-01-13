package com.litongjava.tio.boot.hello.services;

import com.litongjava.jfinal.aop.annotation.AService;
import com.litongjava.tio.boot.hello.model.User;

@AService
public class UserServiceImpl implements UserService {

  @Override
  public User getUser() {
    return User.builder().loginName("Ping E Lee").nick("李通2").ip("127.0.0.1").build();
  }

}
