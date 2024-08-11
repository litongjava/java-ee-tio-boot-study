package com.litongjava.jfinal.plugins.ecache;

import com.litongjava.jfinal.aop.Before;
import com.litongjava.jfinal.plugin.cache.Cacheable;
import com.litongjava.jfinal.plugin.ehcache.EcacheCacheInterceptor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserService {

  @Before(EcacheCacheInterceptor.class)
  //@Cacheable()
  public String getUser(String username) {
    System.out.println("username:" + username);
    return username;
  }
}
