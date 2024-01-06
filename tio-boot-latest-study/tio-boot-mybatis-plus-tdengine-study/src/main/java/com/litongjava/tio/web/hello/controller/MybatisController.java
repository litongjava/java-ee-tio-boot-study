package com.litongjava.tio.web.hello.controller;

import com.litongjava.jfinal.aop.annotation.AAutowired;
import com.litongjava.tio.http.server.annotation.RequestPath;
import lombok.Cleanup;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionManager;

import java.sql.Connection;

/**
 * Created by litonglinux@qq.com on 1/6/2024_5:45 AM
 */
@RequestPath("/mybatis")
public class MybatisController {

  @AAutowired
  private SqlSessionManager sqlSessionManager;

  public String session() {
    @Cleanup
    SqlSession sqlSession = sqlSessionManager.openSession();
    Connection connection = sqlSession.getConnection();
    return connection.toString();
  }
}
