package com.litongjava.jfinal.plugins.redis;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.redis.RedisPlugin;

/**
 * Created by litonglinux@qq.com on 12/30/2023_1:00 AM
 */
public class RedisUserDemo {

  public static void main(String[] args) {
    // 用于缓存bbs模块的redis服务
    RedisPlugin bbsRedis = new RedisPlugin("bbs", "localhost");
    bbsRedis.start();

    UserService userService = Aop.get(UserService.class);
    userService.getUser("litongjava");
    userService.getUser("litongjava");
    String username = userService.getUser("litongjava");
    System.out.println("result:"+username);
    bbsRedis.stop();
  }
}
