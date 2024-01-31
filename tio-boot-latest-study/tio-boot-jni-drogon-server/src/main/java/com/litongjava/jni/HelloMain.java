package com.litongjava.jni;

public class HelloMain {
  public static void main(String[] args) {
    DrogonCppServer drogonCppServer = new DrogonCppServer();
    drogonCppServer.start(8081);
  }
}
