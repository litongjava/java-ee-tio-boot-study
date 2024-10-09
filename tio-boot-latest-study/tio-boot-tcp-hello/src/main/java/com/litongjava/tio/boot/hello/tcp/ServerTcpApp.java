package com.litongjava.tio.boot.hello.tcp;

import com.litongjava.annotation.AComponentScan;
import com.litongjava.tio.boot.TioApplication;

@AComponentScan
public class ServerTcpApp {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    TioApplication.run(ServerTcpApp.class, args);
    long end = System.currentTimeMillis();
    System.out.println(end - start + "(ms)");
  }
}
