package com.litongjava.jfinal.plugins.redis;

import java.util.ArrayList;
import java.util.List;

import com.litongjava.jfinal.plugins.model.User;
import com.litongjava.redis.Redis;
import com.litongjava.redis.RedisPlugin;

public class HashDemoWithUser {

  public static void main(String[] args) {
    // 用于缓存bbs模块的redis服务
    RedisPlugin bbsRedis = new RedisPlugin("main", "192.168.3.9", 6379, 3000, "123456");
    bbsRedis.start();

    List<User> list = new ArrayList<>();
    list.add(new User("Tong Li", "0000000"));
    list.add(new User("Tong Li1", "0000000"));

    // set list
    Redis.use().hsetList("user", "001", list);
    // get list
    List<User> hgetList = Redis.use().hgetList("user", "001", User.class);
    System.out.println(hgetList.size());
    bbsRedis.stop();
  }
}
