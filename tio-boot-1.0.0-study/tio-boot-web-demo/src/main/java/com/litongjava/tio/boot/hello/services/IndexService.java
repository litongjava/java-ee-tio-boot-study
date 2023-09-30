package com.litongjava.tio.boot.hello.services;

import com.litongjava.tio.boot.hello.model.User;

public class IndexService {

  public User getUser() {
    User user = User.builder().loginName("Ping E Lee").nick("李通").ip("127.0.0.1").build();
    return user;
  }

}
