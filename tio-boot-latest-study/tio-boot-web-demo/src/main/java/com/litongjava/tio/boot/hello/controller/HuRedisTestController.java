package com.litongjava.tio.boot.hello.controller;

import cn.hutool.db.nosql.redis.RedisDS;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.server.annotation.RequestPath;
import lombok.extern.slf4j.Slf4j;

@RequestPath("/huredis")
@Slf4j
public class HuRedisTestController {

  @RequestPath("/test")
  public String test(HttpRequest request) {
    RedisDS redisDS = Aop.get(RedisDS.class);
    String value = redisDS.getStr("key");
    if (value == null) {
      log.info("计算新的value");
      redisDS.setStr("key", "value");
    }
    return value;
  }
}
