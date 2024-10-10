package com.issues01;

import com.litongjava.jfinal.aop.AopBefore;

public class Cat {
  @AopBefore(Aspect1.class)
  public String eat() {
    return "eat chat";
  }
}