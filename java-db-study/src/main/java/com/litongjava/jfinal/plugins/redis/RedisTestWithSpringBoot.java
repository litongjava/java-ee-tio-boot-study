package com.litongjava.jfinal.plugins.redis;

import com.litongjava.redis.Redis;
import com.litongjava.redis.RedisPlugin;
import com.litongjava.redis.serializer.FstSerializer;

public class RedisTestWithSpringBoot {
  public static void main(String[] args) {
    RedisPlugin rp = new RedisPlugin("myRedis", "192.168.1.2", 6379, 3000, "123456");
    rp.start();
    Redis.use().getJedis().set(FstSerializer.me.valueToBytes("key"), FstSerializer.me.valueToBytes("value"));

    byte[] bs = Redis.use().getJedis().get(FstSerializer.me.keyToBytes("key"));

    Object valueFromBytes = FstSerializer.me.valueFromBytes(bs);
    System.out.println(valueFromBytes);

    // Redis.use().set("key", "value");
    // Redis.use().get("key");
  }
}
