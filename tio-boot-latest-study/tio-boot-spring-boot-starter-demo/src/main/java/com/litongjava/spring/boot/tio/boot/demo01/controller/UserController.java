package com.litongjava.spring.boot.tio.boot.demo01.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.litongjava.annotation.RequestPath;
import com.litongjava.spring.boot.tio.boot.demo01.service.UserService;

@RequestPath("/user")
public class UserController {

  @Autowired
  private UserService userService;

  public List<String> listAll() {
    return userService.listAll();
  }
}
