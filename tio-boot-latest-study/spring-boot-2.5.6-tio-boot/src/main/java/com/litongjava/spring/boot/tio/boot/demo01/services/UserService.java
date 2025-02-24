package com.litongjava.spring.boot.tio.boot.demo01.services;

import java.util.List;

import com.litongjava.db.activerecord.Row;

public interface UserService {
  List<Row> listAll();
}
