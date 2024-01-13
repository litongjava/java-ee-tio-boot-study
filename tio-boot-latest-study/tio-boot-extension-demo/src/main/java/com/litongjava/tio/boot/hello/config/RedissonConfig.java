package com.litongjava.tio.boot.hello.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import com.litongjava.jfinal.aop.annotation.ABean;
import com.litongjava.jfinal.aop.annotation.AConfiguration;

@AConfiguration
public class RedissonConfig {

  @ABean(destroyMethod = "shutdown", priority = 10)
  public RedissonClient redissonClient() {
    Config config = new Config();
    config.useSingleServer().setAddress("redis://localhost:6379").setDatabase(0);

    // 如果你的 Redis 设置了密码
    // .setPassword("yourPassword");
    RedissonClient client = null;
    try {
      client = Redisson.create(config);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return client;
  }
}