package com.litongjava.tio.web.hello.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionManager;

import com.litongjava.jfinal.aop.annotation.AAutowired;
import com.litongjava.tio.web.hello.mapper.Stable2Mapper;
import com.litongjava.tio.web.hello.model.Stable2;

import lombok.Cleanup;

public class Stable2Service {

  @AAutowired
  private SqlSessionManager sqlSessionManager;

  public List<Stable2> selectList() {
    @Cleanup
    SqlSession sqlSession = sqlSessionManager.openSession(false);
    return sqlSession.getMapper(Stable2Mapper.class).selectList(null);
  }
}
