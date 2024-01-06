package com.litongjava.tio.web.hello.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionManager;

import com.litongjava.jfinal.aop.annotation.AAutowired;
import com.litongjava.tio.web.hello.mapper.Stable4Mapper;
import com.litongjava.tio.web.hello.model.Stable4;

import lombok.Cleanup;

public class Stable4Service {

  @AAutowired
  private SqlSessionManager sqlSessionManager;

  public List<Stable4> selectList() {
    @Cleanup
    SqlSession sqlSession = sqlSessionManager.openSession(false);
    return sqlSession.getMapper(Stable4Mapper.class).selectList(null);
  }
}
