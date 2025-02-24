package com.litongjava.spring.boot.tio.boot.demo01.services;

import java.util.List;

import com.litongjava.annotation.AService;
import com.litongjava.db.activerecord.Db;
import com.litongjava.db.activerecord.Row;

@AService
public class UserServiceImpl implements UserService {

  public List<Row> listAll() {
    List<Row> all = Db.findAll("user");
    return all;
  }
}
