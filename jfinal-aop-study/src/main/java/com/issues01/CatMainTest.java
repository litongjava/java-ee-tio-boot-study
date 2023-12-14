package com.issues01;

import com.jfinal.aop.Aop;
import com.litongjava.hotswap.wrapper.app.SimpleApp;

//import com.jfinal.aop.Aop;

public class CatMainTest {

  public static void main(String[] args) {
    SimpleApp.run(CatMainTest.class.getName(), "index");
  }

  public void index() {
    String javaVersion = System.getProperty("java.version");
    System.out.println("java-version:" + javaVersion);
    // ProxyManager.me().setProxyFactory(new CglibProxyFactory());
    Cat cat = Aop.get(Cat.class);
    cat.eat();
  }
}
