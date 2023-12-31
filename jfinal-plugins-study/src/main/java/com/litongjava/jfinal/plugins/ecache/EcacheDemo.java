package com.litongjava.jfinal.plugins.ecache;


import com.litongjava.jfinal.aop.Aop;
import com.litongjava.jfinal.plugin.ehcache.EhCachePlugin;

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
