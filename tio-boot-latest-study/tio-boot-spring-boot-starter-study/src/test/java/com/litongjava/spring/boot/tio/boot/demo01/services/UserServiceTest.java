package com.litongjava.spring.boot.tio.boot.demo01.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.jfinal.plugin.activerecord.Record;
import com.litongjava.spring.boot.tio.boot.demo01.Applicaton;
import com.litongjava.spring.boot.tio.boot.demo01.service.UserService;
import com.litongjava.tio.boot.TioApplication;

public class UserServiceTest {

  @Before
  public void before() {
    List<String> list = new ArrayList<String>();
    list.add("--tio.noServer=true");
    String[] args = list.toArray(new String[] {});
    TioApplication.run(Applicaton.class, args);
  }

  @Test
  public void test() {
    UserService userService = Aop.get(UserService.class);
    List<Record> listAll = userService.listAll();
    System.out.println(listAll);
  }
}
