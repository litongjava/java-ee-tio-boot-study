package com.issues02;

import com.litongjava.jfinal.aop.annotation.AService;

@AService
public class DemoServiceImpl implements DemoService {

  public String Hello(){
    return "Hello";
  }
}
