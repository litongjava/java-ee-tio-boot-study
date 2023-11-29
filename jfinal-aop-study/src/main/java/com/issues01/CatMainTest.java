package com.issues01;

import com.litongjava.jfinal.aop.Aop;

//import com.jfinal.aop.Aop;

public class CatMainTest {

  public static void main(String[] args) {
    String javaVersion = System.getProperty("java.version");
    System.out.println("java-version:" + javaVersion);
    // ProxyManager.me().setProxyFactory(new CglibProxyFactory());
    Cat cat = Aop.get(Cat.class);
    cat.eat();
  }
}
