package com.litongjava.tio.web.hello.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionManager;

import com.litongjava.jfinal.aop.annotation.AAutowired;
import com.litongjava.tio.web.hello.mapper.Stable1Mapper;
import com.litongjava.tio.web.hello.model.Stable1;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Stable1Service {

  @AAutowired
  private SqlSessionManager sqlSessionManager;
  public List<Stable1> selectList() {
    @Cleanup
    SqlSession sqlSession = sqlSessionManager.openSession(false);
    log.info("sqlSession:{}", sqlSession);
    Stable1Mapper userMapper = sqlSession.getMapper(Stable1Mapper.class);
    
    log.info("userMapper:{}", userMapper);
    List<Stable1> selectList = userMapper.selectList(null);
    return selectList;
  }
}
