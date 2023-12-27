package com.litongjava.tio.boot.hello.config;

import java.util.List;

import com.litongjava.jfinal.aop.annotation.Bean;
import com.litongjava.jfinal.aop.annotation.Configuration;
import com.litongjava.tio.boot.hello.model.CacheName;
import com.litongjava.tio.boot.hello.services.CacheNameService;
import com.litongjava.tio.utils.cache.caffeine.CaffeineCacheFactory;

@Configuration
public class CacheNameConfig {

  @Bean
  public CacheNameService register() {

    CacheNameService cacheNameService = new CacheNameService();
    List<CacheName> names = cacheNameService.cacheNames();
    for (CacheName cacheName : names) {
      String name = cacheName.getName();
      Long timeToLiveSeconds = cacheName.getTimeToLiveSeconds();
      Long timeToIdleSeconds = cacheName.getTimeToIdleSeconds();
      CaffeineCacheFactory.INSTANCE.register(name, timeToLiveSeconds, timeToIdleSeconds);

    }
    return cacheNameService;
  }
}
