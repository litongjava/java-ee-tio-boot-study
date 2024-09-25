package com.litongjava.spring.boot.tio.boot.demo01.services;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.litongjava.db.activerecord.Record;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.spring.boot.tio.boot.demo01.Applicaton;
import com.litongjava.spring.boot.tio.boot.demo01.service.UserService;
import com.litongjava.tio.boot.tesing.TioBootTest;

public class UserServiceTest {

  @Before
  public void before() {
    TioBootTest.before(Applicaton.class);
  }

  @Test
  public void test() {
    UserService userService = Aop.get(UserService.class);
    List<Record> listAll = userService.listAll();
    System.out.println(listAll);
  }
}
