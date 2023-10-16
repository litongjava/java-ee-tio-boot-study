package com.litongjava.tio.boot.hello;

import com.litongjava.hotswap.debug.Diagnostic;
import com.litongjava.hotswap.wrapper.tio.boot.TioApplicationWrapper;
import com.litongjava.tio.boot.TioApplication;
import com.litongjava.tio.boot.annotation.ComponentScan;
import com.litongjava.tio.boot.context.Context;
import org.tio.http.server.HttpServerStarter;
import org.tio.server.TioServer;
import org.tio.utils.jfinal.P;

@ComponentScan
public class TioBootWebApp {

  public static void main(String[] args) throws Exception {
    long start = System.currentTimeMillis();
    P.use("app.properties");
//    Context context = TioApplication.run(TioBootWebApp.class, args);
    Diagnostic.setDebug(true);
    Context context = TioApplicationWrapper.run(TioBootWebApp.class, args);
    long end = System.currentTimeMillis();

    System.out.println("started:" + (end - start) + "(ms)");

//    start = System.currentTimeMillis();
//    // 关闭Spring容器,等于关闭spring,同时也等于关闭web中间件,因为web中间件在spring的容器中
//    context.close();
//    HttpServerStarter server = context.getServer();
//    TioServer tioServer = server.getTioServer();
//    boolean waitingStop = tioServer.isWaitingStop();
//    System.out.println(waitingStop);
//    for (int i = 0; i < 10; i++) {
//      Thread.sleep(1 * 1000);
//      waitingStop = context.getServer().getTioServer().isWaitingStop();
//      System.out.println(waitingStop);
//    }
//
//    context = TioApplication.run(TioBootWebApp.class, args);
//    System.out.println("started:" + (end - start) + "(ms)");
  }
}