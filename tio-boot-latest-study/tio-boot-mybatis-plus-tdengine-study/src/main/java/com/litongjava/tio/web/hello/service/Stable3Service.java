package com.litongjava.tio.web.hello.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionManager;

import com.litongjava.jfinal.aop.annotation.AAutowired;
import com.litongjava.tio.web.hello.mapper.Stable3Mapper;
import com.litongjava.tio.web.hello.model.Stable3;

import lombok.Cleanup;

public class Stable3Service {

  @AAutowired
  private SqlSessionManager sqlSessionManager;

  public List<Stable3> selectList() {
    @Cleanup
    SqlSession sqlSession = sqlSessionManager.openSession(false);
    return sqlSession.getMapper(Stable3Mapper.class).selectList(null);
  }
}
