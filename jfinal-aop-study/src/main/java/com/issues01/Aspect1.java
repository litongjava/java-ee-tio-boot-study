package com.issues01;

import com.litongjava.jfinal.aop.Interceptor;
import com.litongjava.jfinal.aop.Invocation;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Arrays;


@Slf4j
public class Aspect1 implements Interceptor {

  @Override
  public void intercept(Invocation invocation) {
    System.out.println("Before Aspect1 invoking");
    Method method = invocation.getMethod();
    String methodName = invocation.getMethodName();
    Object[] args = invocation.getArgs();
    Object target = invocation.getTarget();
    log.info("method:{}", method);
    log.info("methodName:{}", methodName);
    log.info("args:{}", Arrays.toString(args));
    log.info("target:{}", target);
    Object invoke = invocation.invoke();
    invocation.setReturnValue("set new value");
    log.info("invoke:{}", invoke);

    System.out.println("After Aspect1 invoking");
  }
}
