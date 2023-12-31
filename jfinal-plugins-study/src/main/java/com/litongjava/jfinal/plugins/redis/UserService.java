package com.litongjava.jfinal.plugins.redis;

import com.litongjava.jfinal.aop.Before;
import com.litongjava.jfinal.plugin.cache.Cacheable;
import com.litongjava.jfinal.plugin.redis.RedisCacheInterceptor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserService {

  @Before(RedisCacheInterceptor.class)
  @Cacheable(name = "userService", value = "getUser", ttl = 300)
  public String getUser(String username) {
    System.out.println("select from db username:" + username);
    return username;
  }
}
