package com.litongjava.tio.boot.hello.validator;

import com.litongjava.jfinal.aop.Interceptor;
import com.litongjava.jfinal.aop.Invocation;

public class LoginValidator implements Interceptor {

  @Override
  public void intercept(Invocation invocation) {
    System.out.println("Before Aspect1 invoking");
    invocation.invoke();
    System.out.println("After Aspect1 invoking");
  }
}