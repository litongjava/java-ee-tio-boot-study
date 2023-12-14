package com.issues01;

//import com.litongjava.jfinal.aop.Interceptor;
//import com.litongjava.jfinal.aop.Invocation;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class Aspect1 implements Interceptor {

  @Override
  public void intercept(Invocation invocation) {
    System.out.println("Before Aspect1 invoking");
    invocation.invoke();
    System.out.println("After Aspect1 invoking");
  }
}
