package com.litongjava.jfinal.plugins.redis;

import com.litongjava.jfinal.aop.Before;
import com.litongjava.jfinal.plugin.cache.CacheName;
import com.litongjava.jfinal.plugin.redis.RedisCacheInterceptor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by litonglinux@qq.com on 12/17/2023_4:56 PM
 */
@Slf4j
public class UserService {

  @Before(RedisCacheInterceptor.class)
  @CacheName("user")
  public String getUser(String username) {
    System.out.println("username:" + username);
    return username;
  }
}
