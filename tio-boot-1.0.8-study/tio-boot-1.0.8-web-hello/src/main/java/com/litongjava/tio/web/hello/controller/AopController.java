package com.litongjava.tio.web.hello.controller;

import org.tio.http.server.annotation.RequestPath;

import com.litongjava.jfinal.aop.Aop;

import lombok.extern.slf4j.Slf4j;

@RequestPath("/aop")
@Slf4j
public class AopController {
  
  public AopController() {
    log.info("xxx:{}",this.getClass().getClassLoader().toString()); 
  }
  
  @RequestPath("/beans")
  public String[] beans() {
    String[] beans = Aop.beans();
    return beans;
  }
  
  @RequestPath("/classloader")
  public String classloader() {
    return this.getClass().getClassLoader().toString();
  }

}
