package com.litongjava.tio.web.hello.controller;

import com.litongjava.annotation.Inject;
import com.litongjava.annotation.RequestPath;
import com.litongjava.tio.web.hello.service.UserService;

@RequestPath("/user")
public class UserController {


  @Inject
  private UserService userService;
  
  public String index() {
    userService.index();
    return "index";
  }
}
