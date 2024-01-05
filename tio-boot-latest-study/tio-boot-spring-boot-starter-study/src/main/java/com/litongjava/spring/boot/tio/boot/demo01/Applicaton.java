package com.litongjava.spring.boot.tio.boot.demo01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.litongjava.jfinal.aop.annotation.AComponentScan;

@SpringBootApplication
@AComponentScan
public class Applicaton {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    SpringApplication.run(Applicaton.class, args);
    long end = System.currentTimeMillis();
    System.out.println(end - start + "(ms)");
  }
}
