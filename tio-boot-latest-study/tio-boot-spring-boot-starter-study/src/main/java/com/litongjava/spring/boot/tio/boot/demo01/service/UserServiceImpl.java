package com.litongjava.spring.boot.tio.boot.demo01.service;

import java.util.List;

import com.litongjava.jfinal.aop.annotation.AService;
import com.litongjava.jfinal.plugin.activerecord.Db;
import com.litongjava.jfinal.plugin.activerecord.Record;

@AService
public class UserServiceImpl implements UserService {

  public List<Record> listAll() {
    return Db.findAll("user");
  }
}
