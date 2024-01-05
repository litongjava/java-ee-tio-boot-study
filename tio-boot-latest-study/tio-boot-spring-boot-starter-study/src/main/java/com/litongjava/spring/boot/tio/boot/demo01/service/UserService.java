package com.litongjava.spring.boot.tio.boot.demo01.service;

import java.util.List;

import com.litongjava.jfinal.plugin.activerecord.Record;

public interface UserService {
  List<Record> listAll();
}
