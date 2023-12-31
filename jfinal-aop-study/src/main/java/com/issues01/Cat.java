package com.issues01;

import com.litongjava.jfinal.aop.Before;

public class Cat {
  @Before(Aspect1.class)
  public String eat() {
    return "eat chat";
  }
}