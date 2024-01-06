package com.litongjava.tio.web.hello.config;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionManager;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.litongjava.jfinal.aop.annotation.AAutowired;
import com.litongjava.jfinal.aop.annotation.ABean;
import com.litongjava.jfinal.aop.annotation.AConfiguration;

@AConfiguration
public class MybatisPlusConfig {

  @AAutowired
  private DataSource dataSource;

  @ABean(destroyMethod = "close")
  public SqlSessionManager getSqlSession() {
    // environment
    Environment environment = new Environment("default", new JdbcTransactionFactory(), dataSource);
    // MybatisConfiguration
    org.apache.ibatis.session.Configuration configuration = new MybatisConfiguration();
    configuration.setEnvironment(environment);
    configuration.addMappers("com.litongjava.tio.web.hello.mapper");

    // MybatisSqlSessionFactoryBuilder
    MybatisSqlSessionFactoryBuilder builder = new MybatisSqlSessionFactoryBuilder();
    SqlSessionFactory sqlSessionFactory = builder.build(configuration);
    SqlSessionManager sqlSessionManager = SqlSessionManager.newInstance(sqlSessionFactory);
    return sqlSessionManager;
  }
}

