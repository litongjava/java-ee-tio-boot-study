package com.litongjava.tio.boot.hello;

import org.tio.utils.jfinal.P;

import com.litongjava.tio.http.server.boot.TioApplication;

public class TioBootWebApp {

  public static void main(String[] args) throws Exception {
    long start = System.currentTimeMillis();
    P.use("app.properties");
    TioApplication.run(TioBootWebApp.class, args);
    long end = System.currentTimeMillis();

    System.out.println("started:" + (end - start) + "(ms)");
  }
}