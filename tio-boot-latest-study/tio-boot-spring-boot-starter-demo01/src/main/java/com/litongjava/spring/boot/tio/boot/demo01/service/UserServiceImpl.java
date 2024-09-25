package com.litongjava.spring.boot.tio.boot.demo01.service;

import java.util.List;

import com.litongjava.db.activerecord.Db;
import com.litongjava.db.activerecord.Record;
import com.litongjava.jfinal.aop.annotation.AService;

@AService
public class UserServiceImpl implements UserService {

  public List<Record> listAll() {
    return Db.findAll("user");
  }
}
