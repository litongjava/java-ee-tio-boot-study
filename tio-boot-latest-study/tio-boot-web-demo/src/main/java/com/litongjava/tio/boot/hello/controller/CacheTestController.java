package com.litongjava.tio.boot.hello.controller;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.jfinal.aop.annotation.Controller;
import com.litongjava.tio.boot.hello.services.CacheNameService;
import com.litongjava.tio.boot.hello.services.CacheService;
import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.utils.cache.CacheUtils;
import com.litongjava.tio.utils.cache.FirsthandCreater;
import com.litongjava.tio.utils.cache.ICache;
import com.litongjava.tio.utils.cache.caffeine.CaffeineCache;
import com.litongjava.tio.utils.cache.redis.RedisCache;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestPath("/cache")
public class CacheTestController {

  @RequestPath("/test")
  public Object test() {
    CacheService cacheService = Aop.get(CacheService.class);
    String key = "cache-test1234";
    Object value = cacheService.get(key);
    if (value == null) {
      log.info("计算新的value");
      value = "12343";
      cacheService.put(key, value);
    }
    return value;
  }

  @RequestPath("/test2")
  public Object test2() {
    // firsthandCreater用户查询数据库
    FirsthandCreater<String> firsthandCreater = new FirsthandCreater<String>() {
      @Override
      public String create() {
        log.info("查询数据库");
        return "index";
      }
    };

    String cacheName = Aop.get(CacheNameService.class).getDemo().getName();
    ICache cache = CaffeineCache.getCache(cacheName);
    String key = "key";
    boolean putTempToCacheIfNull = false;
    String value = CacheUtils.get(cache, key, putTempToCacheIfNull, firsthandCreater);
    return value;
  }
  
  @RequestPath("/test3")
  public Object test3() {
    // firsthandCreater用户查询数据库
    FirsthandCreater<String> firsthandCreater = new FirsthandCreater<String>() {
      @Override
      public String create() {
        log.info("查询数据库");
        return "index";
      }
    };

    String cacheName = Aop.get(CacheNameService.class).getDemo().getName();
    ICache cache = RedisCache.getCache(cacheName);
    String key = "key";
    boolean putTempToCacheIfNull = false;
    String value = CacheUtils.get(cache, key, putTempToCacheIfNull, firsthandCreater);
    return value;
  }
}
