package com.issues02;

import com.litongjava.jfinal.aop.annotation.AAutowired;
import com.litongjava.jfinal.aop.annotation.AController;

@AController
public class DemoController {

  @AAutowired
  private DemoService demoService;

  public String hello() {
    return demoService.Hello();
  }
}
