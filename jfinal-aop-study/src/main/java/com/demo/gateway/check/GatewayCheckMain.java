package com.demo.gateway.check;

import com.litongjava.jfinal.aop.Aop;

public class GatewayCheckMain {

  public static void main(String[] args) {
    new GatewayCheckMain().index();
  }

  public void index() {
    String javaVersion = System.getProperty("java.version");
    System.out.println("java-version:" + javaVersion);
    TestController controller = Aop.get(TestController.class);
    System.out.println(controller.toString());
    System.out.println("result:" + controller.index());
  }
}