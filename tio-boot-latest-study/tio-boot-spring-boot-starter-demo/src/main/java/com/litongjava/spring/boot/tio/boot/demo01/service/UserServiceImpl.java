package com.litongjava.spring.boot.tio.boot.demo01.service;

import java.util.ArrayList;
import java.util.List;

import com.litongjava.annotation.AService;

@AService
public class UserServiceImpl implements UserService {

  public List<String> listAll() {
    String user1 = "user1";

    List<String> users = new ArrayList<>();
    users.add(user1);
    return users;
  }
}
