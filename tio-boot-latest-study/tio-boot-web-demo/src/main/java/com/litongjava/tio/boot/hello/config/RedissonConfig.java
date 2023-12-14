package com.litongjava.tio.boot.hello.config;

import com.litongjava.jfinal.aop.annotation.Bean;
import com.litongjava.jfinal.aop.annotation.Configuration;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

@Configuration
public class RedissonConfig {

  @Bean(destroyMethod = "shutdown")
  public RedissonClient redissonClient() {
    Config config = new Config();
    config.useSingleServer()
      .setAddress("redis://localhost:6379")
      .setDatabase(0);

    // 如果你的 Redis 设置了密码
    // .setPassword("yourPassword");

    return Redisson.create(config);
  }
}