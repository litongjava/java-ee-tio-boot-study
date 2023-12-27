package com.litongjava.tio.web.hello.controller;

import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.utils.cache.CacheUtils;
import com.litongjava.tio.utils.cache.FirsthandCreater;
import com.litongjava.tio.utils.cache.ICache;
import com.litongjava.tio.utils.cache.caffeine.CaffeineCacheFactory;

import lombok.extern.slf4j.Slf4j;

@RequestPath("/cache/caffeine")
@Slf4j
public class CacheCaffeineTestController {

  public Object test2() {
    // firsthandCreater用户查询数据库
    FirsthandCreater<String> firsthandCreater = new FirsthandCreater<String>() {
      @Override
      public String create() {
        log.info("查询数据库");
        return "index";
      }
    };

    // 通常是tableName
    String cacheName = "demo";
    ICache cache = CaffeineCacheFactory.INSTANCE.getCache(cacheName);
    String key = "key";
    boolean putTempToCacheIfNull = false;
    String value = CacheUtils.get(cache, key, putTempToCacheIfNull, firsthandCreater);
    return value;
  }

}
