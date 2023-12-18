package com.issues01;

//import com.jfinal.aop.Before;
import com.litongjava.jfinal.aop.Before;

public class Cat {
  @Before(Aspect1.class)
  public String eat() {
    System.out.println("cat eat");
    return "eat chat";
  }
}