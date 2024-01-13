package com.litongjava.tio.boot.hello.config;

import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.litongjava.jfinal.aop.annotation.ABean;
import com.litongjava.jfinal.aop.annotation.AConfiguration;

@AConfiguration
public class CaffeineCacheConfig {
  @ABean
  public Cache<String, Object> caffeineCache() {
    return Caffeine.newBuilder().maximumSize(10000).expireAfterWrite(5, TimeUnit.MINUTES).build();
  }

}
