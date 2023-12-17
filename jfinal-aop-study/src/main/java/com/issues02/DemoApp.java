package com.issues02;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.jfinal.aop.annotation.ComponentScan;
import com.litongjava.jfinal.aop.process.BeanProcess;
import com.litongjava.jfinal.aop.scaner.ComponentScanner;

import java.util.List;

/**
 * Created by litonglinux@qq.com on 12/16/2023_4:10 PM
 */
@ComponentScan
public class DemoApp {
  public static void main(String[] args) throws Exception {
    List<Class<?>> scannedClasses = Aop.scan(DemoApp.class);
    Aop.initAnnotation(scannedClasses);

    DemoController demoController = Aop.get(DemoController.class);
    String hello = demoController.hello();
    System.out.println(hello);
    Aop.close();
  }
}
