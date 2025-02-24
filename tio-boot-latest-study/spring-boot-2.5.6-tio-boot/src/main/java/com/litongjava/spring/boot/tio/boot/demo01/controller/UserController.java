package com.litongjava.spring.boot.tio.boot.demo01.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.litongjava.db.activerecord.Row;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.spring.boot.tio.boot.demo01.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

  @RequestMapping("/listAll")
  public List<Row> listAll() {
    return Aop.get(UserService.class).listAll();
  }
}
