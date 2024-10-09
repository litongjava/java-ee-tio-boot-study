package com.issues02;

import com.litongjava.annotation.AAutowired;
import com.litongjava.annotation.AController;

@AController
public class DemoController {

  @AAutowired
  private DemoService demoService;

  public String hello() {
    return demoService.Hello();
  }
}
