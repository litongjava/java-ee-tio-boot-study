package com.litongjava.tio.web.hello;

import com.litongjava.jfinal.aop.annotation.ComponentScan;
import com.litongjava.tio.boot.TioApplication;
import com.litongjava.tio.server.intf.ServerAioHandler;
import com.litongjava.tio.server.intf.ServerAioListener;
import com.litongjava.tio.web.hello.tcp.DemoHandler;
import com.litongjava.tio.web.hello.tcp.DemoTioServerListener;

@ComponentScan
public class HelloApp {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    ServerAioHandler demoHandler = new DemoHandler();
    ServerAioListener listener = new DemoTioServerListener();
    TioApplication.run(HelloApp.class, demoHandler, listener, args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "ms");
  }
}
