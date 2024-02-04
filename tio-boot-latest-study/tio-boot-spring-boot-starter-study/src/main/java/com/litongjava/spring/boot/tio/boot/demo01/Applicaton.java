package com.litongjava.spring.boot.tio.boot.demo01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.litongjava.jfinal.aop.annotation.AComponentScan;
import com.litongjava.tio.boot.spring.EmbeddedTioBoot;
import com.litongjava.tio.boot.spring.SpringBootArgs;
import com.litongjava.tio.boot.spring.TioBootWebServerFactoryCustomizer;

@SpringBootApplication
@AComponentScan
@Import({ EmbeddedTioBoot.class, TioBootWebServerFactoryCustomizer.class })
public class Applicaton {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    SpringBootArgs.set(Applicaton.class, args);
    SpringApplication.run(Applicaton.class, args);
    long end = System.currentTimeMillis();
    System.out.println(end - start + "(ms)");
  }
}
