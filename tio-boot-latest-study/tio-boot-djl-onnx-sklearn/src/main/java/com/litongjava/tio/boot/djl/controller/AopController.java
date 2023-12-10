package com.litongjava.tio.boot.djl.controller;

import org.tio.http.server.annotation.RequestPath;

import com.litongjava.jfinal.aop.Aop;

@RequestPath("/aop")
public class AopController {

  @RequestPath("/beans")
  public String[] beans() {
    return Aop.beans();
  }

  @RequestPath("/get")
  public String get() {
    return Aop.get(IrisflowerController.class).toString();
  }

  @RequestPath("/get-classloader")
  public String getClassloader() {
    Class<IrisflowerController> targetClass = IrisflowerController.class;
    return targetClass.getClassLoader().toString() + "," + this.getClass().getClassLoader().toString();
  }

}
