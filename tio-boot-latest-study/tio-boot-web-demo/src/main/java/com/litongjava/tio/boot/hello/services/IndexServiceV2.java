package com.litongjava.tio.boot.hello.services;

import com.litongjava.jfinal.aop.annotation.Service;
import com.litongjava.tio.boot.hello.model.User;

@Service
public class IndexServiceV2 implements IndexService {

  public User getUser() {
    User user = User.builder().loginName("Ping E Lee").nick("李通2").ip("127.0.0.1").build();
    return user;
  }

}