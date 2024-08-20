package com.litongjava.jfinal.plugins.redis;

import com.litongjava.redis.Redis;
import com.litongjava.redis.RedisPlugin;

public class RedisTest {
  public static void main(String[] args) {
    RedisPlugin rp = new RedisPlugin("myRedis", "192.168.1.2", 6379, 3000, "123456");
    rp.start();

    Redis.use().set("key", "value");
    Redis.use().get("key");
  }
}
