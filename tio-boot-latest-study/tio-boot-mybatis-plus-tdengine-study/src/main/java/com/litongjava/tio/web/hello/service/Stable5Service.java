package com.litongjava.tio.web.hello.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionManager;

import com.litongjava.jfinal.aop.annotation.AAutowired;
import com.litongjava.tio.web.hello.mapper.Stable5Mapper;
import com.litongjava.tio.web.hello.model.Stable5;

import lombok.Cleanup;

public class Stable5Service {

  @AAutowired
  private SqlSessionManager sqlSessionManager;

  public List<Stable5> selectList() {
    @Cleanup
    SqlSession sqlSession = sqlSessionManager.openSession(false);
    return sqlSession.getMapper(Stable5Mapper.class).selectList(null);
  }
}
