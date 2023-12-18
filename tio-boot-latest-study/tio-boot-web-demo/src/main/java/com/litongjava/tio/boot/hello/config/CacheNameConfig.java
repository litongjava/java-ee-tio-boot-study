package com.litongjava.tio.boot.hello.config;

import java.util.List;

import org.redisson.api.RedissonClient;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.jfinal.aop.annotation.Bean;
import com.litongjava.jfinal.aop.annotation.Configuration;
import com.litongjava.tio.boot.hello.model.CacheName;
import com.litongjava.tio.boot.hello.services.CacheNameService;
import com.litongjava.tio.utils.cache.caffeineredis.CaffeineRedisCache;

@Configuration
public class CacheNameConfig {

  @Bean
  public CacheNameService register() {
    RedissonClient redissonClient = Aop.get(RedissonClient.class);
    CacheNameService cacheNameService = new CacheNameService();
    List<CacheName> names = cacheNameService.cacheNames();
    for (CacheName cacheName : names) {
      // CaffeineCache.register(cacheName.getName(), cacheName.getTimeToLiveSeconds(), cacheName.getTimeToIdleSeconds());
      //基于CAFFEINE和REDIS实现的两级缓存
      CaffeineRedisCache.register(redissonClient,
          // cache
          cacheName.getName(), cacheName.getTimeToLiveSeconds(), cacheName.getTimeToIdleSeconds());

    }
    return cacheNameService;
  }
}
