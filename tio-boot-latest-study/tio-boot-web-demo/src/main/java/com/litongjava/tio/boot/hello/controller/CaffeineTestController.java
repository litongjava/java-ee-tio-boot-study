package com.litongjava.tio.boot.hello.controller;

import com.github.benmanes.caffeine.cache.Cache;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.jfinal.aop.annotation.Controller;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.server.annotation.RequestPath;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestPath("/caffeine")
@Slf4j
public class CaffeineTestController {
  @RequestPath("/test")
  public Object test(HttpRequest request) {
    Cache<String, Object> cache = Aop.get(Cache.class);
    Object value = cache.getIfPresent("key");
    if (value == null) {
      log.info("计算value");
      cache.put("key", "11111");
    }

    return value;
  }
}

