package com.issues02;

import java.util.List;

import com.litongjava.annotation.AComponentScan;
import com.litongjava.jfinal.aop.Aop;

@AComponentScan
public class DemoApp {
  public static void main(String[] args) throws Exception {
    List<Class<?>> scannedClasses = Aop.scan(DemoApp.class);
    Aop.initAnnotation(scannedClasses);

    DemoController demoController = Aop.get(DemoController.class);
    String hello = demoController.hello();
    System.out.println(hello);
    //关闭容器
    Aop.close();
  }
}
