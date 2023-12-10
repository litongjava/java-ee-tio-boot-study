package com.litongjava.tio.boot.djl;

import org.tio.utils.jfinal.P;

import com.litongjava.hotswap.wrapper.tio.boot.TioApplicationWrapper;
import com.litongjava.jfinal.aop.annotation.ComponentScan;

@ComponentScan
public class SklearnWebApp {

  public static void main(String[] args) throws Exception {
    long start = System.currentTimeMillis();
    // 初始化服务器并启动服务器
    P.use("app.properties");
//     Diagnostic.setDebug(true);
    TioApplicationWrapper.run(SklearnWebApp.class, args);
//     ForkApp.run(SklearnWebApp.class, args, true, new SelfRestart());
    long end = System.currentTimeMillis();
    System.out.println("started:" + (end - start) + "(ms)");
  }
}
