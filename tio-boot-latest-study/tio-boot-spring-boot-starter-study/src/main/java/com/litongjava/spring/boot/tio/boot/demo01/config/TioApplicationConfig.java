package com.litongjava.spring.boot.tio.boot.demo01.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.litongjava.spring.boot.tio.boot.demo01.Applicaton;
import com.litongjava.tio.boot.TioApplication;
import com.litongjava.tio.boot.context.Context;

//@Configuration
public class TioApplicationConfig {

  @Bean(destroyMethod = "close")
  public Context myBean(ApplicationArguments args) {
    String[] sourceArgs = args.getSourceArgs();
    return TioApplication.run(Applicaton.class, sourceArgs);
  }
}
