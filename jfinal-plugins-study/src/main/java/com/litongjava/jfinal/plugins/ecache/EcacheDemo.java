package com.litongjava.jfinal.plugins.ecache;


import com.litongjava.jfinal.aop.Aop;
import com.litongjava.jfinal.plugin.ehcache.EhCachePlugin;

/**
 * Created by litonglinux@qq.com on 12/17/2023_4:54 PM
 */
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
