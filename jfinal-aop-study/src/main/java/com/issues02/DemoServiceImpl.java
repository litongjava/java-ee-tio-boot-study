package com.issues02;

import com.litongjava.jfinal.aop.annotation.Service;

@Service
public class DemoServiceImpl implements DemoService {

  public String Hello(){
    return "Hello";
  }
}
