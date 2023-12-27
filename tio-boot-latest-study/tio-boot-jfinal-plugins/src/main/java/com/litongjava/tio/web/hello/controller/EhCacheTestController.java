package com.litongjava.tio.web.hello.controller;

import com.litongjava.jfinal.plugin.ehcache.CacheKit;
import com.litongjava.tio.http.server.annotation.RequestPath;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestPath("/ecache/test")
public class EhCacheTestController {

  public String test01() {
    String cacheName = "student";
    String cacheKey = "litong";

    String cacheData = CacheKit.get(cacheName, cacheKey);

    if (cacheData == null) {
      String result = "001";
      log.info("计算新的值");
      CacheKit.put(cacheName, cacheKey, result);
    }

    return cacheData;
  }

}
