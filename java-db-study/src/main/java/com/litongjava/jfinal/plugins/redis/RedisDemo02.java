package com.litongjava.jfinal.plugins.redis;

import com.alibaba.fastjson2.JSON;
import com.litongjava.jfinal.plugins.model.User;
import com.litongjava.redis.Redis;
import com.litongjava.redis.RedisCache;
import com.litongjava.redis.RedisPlugin;

/**
 * Created by litonglinux@qq.com on 12/17/2023_5:06 PM
 */
public class RedisDemo02 {
  public static void main(String[] args) {

    // 用于缓存bbs模块的redis服务
    RedisPlugin bbsRedis = new RedisPlugin("bbs", "localhost");
    bbsRedis.start();

    RedisCache bbsCache = Redis.use("bbs");
    // 存储String类型
    bbsCache.set("key", "value___001");
    Object value = bbsCache.get("key");
    System.out.println(value);

    // 存储Object类型
    Object obj = new String("value___001");
    bbsCache.set("key_object", obj);
    value = bbsCache.get("key_object");
    System.out.println(value);

    // 使用 lambda 开放 Jedis API
    Long ret = Redis.call(j -> j.incrBy("increase", 1));
    System.out.println(ret);

    User ping = new User("ping", "00000000");

    // 或者简化为下面代码
    Redis.call(j -> {
      return j.set("user", JSON.toJSONString(ping));
    });

    User user1 = Redis.call(jedis -> {
      String user = jedis.get("user");
      return JSON.parseObject(user, User.class);
    });

    System.out.println(user1);

  }
}
