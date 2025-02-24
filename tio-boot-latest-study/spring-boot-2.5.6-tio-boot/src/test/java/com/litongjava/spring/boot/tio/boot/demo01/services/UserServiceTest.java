package com.litongjava.spring.boot.tio.boot.demo01.services;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.litongjava.db.activerecord.Row;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.spring.boot.tio.boot.demo01.Applicaton;
import com.litongjava.tio.boot.testing.TioBootTest;

public class UserServiceTest {

  @Before
  public void before() {
    try {
      TioBootTest.run(Applicaton.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void test() {
    UserService userService = Aop.get(UserService.class);
    List<Row> listAll = userService.listAll();
    System.out.println(listAll);
  }

}
