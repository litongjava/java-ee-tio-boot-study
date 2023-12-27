package com.litongjava.tio.web.hello.controller;

import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.utils.cache.CacheUtils;
import com.litongjava.tio.utils.cache.FirsthandCreater;
import com.litongjava.tio.utils.cache.ICache;
import com.litongjava.tio.utils.cache.caffeineredis.CaffeineRedisCacheFactory;

import lombok.extern.slf4j.Slf4j;

@RequestPath("/cache/caffeine/redis")
@Slf4j
public class CacheCaffeineRedisTestController {

  public Object test() {
    // firsthandCreater用户查询数据库
    FirsthandCreater<String> firsthandCreater = new FirsthandCreater<String>() {
      @Override
      public String create() {
        log.info("查询数据库");
        return "index";
      }
    };

    String cacheName = "demo";
    ICache cache = CaffeineRedisCacheFactory.INSTANCE.getCache(cacheName);
    String key = "key";
    boolean putTempToCacheIfNull = false;
    String value = CacheUtils.get(cache, key, putTempToCacheIfNull, firsthandCreater);
    return value;
  }
}
