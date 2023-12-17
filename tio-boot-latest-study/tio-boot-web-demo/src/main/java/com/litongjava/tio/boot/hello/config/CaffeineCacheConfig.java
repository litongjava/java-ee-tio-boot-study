package com.litongjava.tio.boot.hello.config;

import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.litongjava.jfinal.aop.annotation.Bean;
import com.litongjava.jfinal.aop.annotation.Configuration;

@Configuration
public class CaffeineCacheConfig {
  @Bean
  public Cache<String, Object> caffeineCache() {
    return Caffeine.newBuilder()
      .maximumSize(10000)
      .expireAfterWrite(5, TimeUnit.MINUTES)
      .build();
  }

}
