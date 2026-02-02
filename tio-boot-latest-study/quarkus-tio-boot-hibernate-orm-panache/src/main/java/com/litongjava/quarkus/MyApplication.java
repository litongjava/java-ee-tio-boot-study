package com.litongjava.quarkus;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class MyApplication {

  public static void main(String... args) {
    long start = System.currentTimeMillis();
    Quarkus.run(args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "(ms)");
  }
}