package com.litongjava.jni;

public class DrogonCppServer {

  static {
    System.loadLibrary("hello");
  }
  public native void start(int port);

}
