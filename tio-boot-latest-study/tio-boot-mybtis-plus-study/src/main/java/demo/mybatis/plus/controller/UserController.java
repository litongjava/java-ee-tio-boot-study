package demo.mybatis.plus.controller;

import java.util.List;

import com.litongjava.jfinal.aop.Inject;
import com.litongjava.tio.http.server.annotation.RequestPath;

import demo.mybatis.plus.model.User;
import demo.mybatis.plus.services.UserServcie;

@RequestPath("/user")
public class UserController {

  @Inject
  private UserServcie userServcie;

  public List<User> selectList() {
    return userServcie.selectList();
  }
}