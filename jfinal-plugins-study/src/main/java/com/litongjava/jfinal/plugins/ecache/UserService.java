package com.litongjava.jfinal.plugins.ecache;

import com.litongjava.jfinal.aop.Before;
import com.litongjava.jfinal.plugin.ehcache.EcacheCacheInterceptor;
import com.litongjava.jfinal.plugin.ehcache.EcacheCacheName;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by litonglinux@qq.com on 12/17/2023_4:56 PM
 */
@Slf4j
public class UserService {

  @Before(EcacheCacheInterceptor.class)
  @EcacheCacheName("user")
  public String getUser(String username){
    System.out.println("username:"+username);
    return username;
  }
}
