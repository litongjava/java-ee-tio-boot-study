package com.litongjava.tio.boot.hello.services;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;

import com.github.benmanes.caffeine.cache.Cache;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.jfinal.aop.annotation.AService;

@AService
public class CacheService {

  private final Cache<String, Object> caffeineCache;
  private final RedissonClient redissonClient;

  public CacheService() {
    this.caffeineCache = Aop.get(Cache.class);
    this.redissonClient = Aop.get(RedissonClient.class);
  }

  public Object get(String key) {
    // 首先尝试从 Caffeine 缓存中获取数据
    Object value = caffeineCache.getIfPresent(key);
    if (value != null) {
      return value;
    }

    // 如果 Caffeine 缓存中没有，则尝试从 Redisson 缓存中获取
    RBucket<Object> bucket = redissonClient.getBucket(key);
    value = bucket.get();

    if (value != null) {
      // 如果在 Redisson 中找到数据，则将其添加到 Caffeine 缓存中
      caffeineCache.put(key, value);
    }

    return value;
  }

  public void put(String key, Object value) {
    // 同时更新 Caffeine 和 Redisson 缓存
    caffeineCache.put(key, value);
    RBucket<Object> bucket = redissonClient.getBucket(key);
    bucket.set(value);
  }
}
