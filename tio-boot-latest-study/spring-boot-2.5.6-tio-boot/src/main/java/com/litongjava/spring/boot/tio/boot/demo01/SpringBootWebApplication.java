package com.litongjava.spring.boot.tio.boot.demo01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.litongjava.annotation.AComponentScan;
import com.litongjava.tio.boot.TioApplication;

@SpringBootApplication
@AComponentScan
public class SpringBootWebApplication {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    SpringApplication.run(SpringBootWebApplication.class, args);
    TioApplication.run(SpringBootWebApplication.class, args);
    long end = System.currentTimeMillis();
    System.out.println(end - start + "(ms)");
  }
}