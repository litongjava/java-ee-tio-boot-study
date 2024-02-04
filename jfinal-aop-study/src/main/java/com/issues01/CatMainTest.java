package com.issues01;

import com.litongjava.jfinal.aop.Aop;


public class CatMainTest {

  public static void main(String[] args) {
    new CatMainTest().index();
  }

  public void index() {
    String javaVersion = System.getProperty("java.version");
    System.out.println("java-version:" + javaVersion);
    // ProxyManager.me().setProxyFactory(new CglibProxyFactory());
    Cat cat = Aop.get(Cat.class);
    String eat = cat.eat();
    System.out.println("result:" + eat);
  }
}
