package com.litongjava.jfinal.plugins.ecache;


import com.litongjava.ehcache.EhCachePlugin;
import com.litongjava.jfinal.aop.Aop;

public class EcacheDemo {
  public static void main(String[] args) {
    EhCachePlugin ehCachePlugin = new EhCachePlugin();
    ehCachePlugin.start();
    UserService userService = Aop.get(UserService.class);
    userService.getUser("litong");
    userService.getUser("litong001");
    userService.getUser("litong");
    ehCachePlugin.stop();
  }
}
