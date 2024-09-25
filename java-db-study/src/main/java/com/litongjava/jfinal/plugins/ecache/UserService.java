package com.litongjava.jfinal.plugins.ecache;


import com.litongjava.ehcache.EhCacheInterceptor;
import com.litongjava.jfinal.aop.Before;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserService {

  @Before(EhCacheInterceptor.class)
  // @Cacheable()
  public String getUser(String username) {
    System.out.println("username:" + username);
    return username;
  }
}
