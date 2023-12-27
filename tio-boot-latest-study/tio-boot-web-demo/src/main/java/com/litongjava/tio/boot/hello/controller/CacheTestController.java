package com.litongjava.tio.boot.hello.controller;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.jfinal.aop.annotation.Controller;
import com.litongjava.tio.boot.hello.services.CacheService;
import com.litongjava.tio.http.server.annotation.RequestPath;

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
}
