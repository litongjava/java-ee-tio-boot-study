package com.litongjava.tio.duboot.demo001.config;

import org.junit.Test;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.tio.dubbo.demo001.service.HelloService;
import com.litongjava.tio.dubbo.demo001.service.impl.HelloServiceImpl;

public class DubboConfigTest {

  @Test
  public void test() {
    HelloService helloService = Aop.get(HelloServiceImpl.class);
    HelloService helloService2 = Aop.get(HelloService.class);
    System.out.println(helloService);
    System.out.println(helloService2);
    
  }

}
