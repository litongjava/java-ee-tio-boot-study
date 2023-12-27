package com.litongjava.tio.web.hello.config;

import com.litongjava.jfinal.aop.annotation.Bean;
import com.litongjava.jfinal.aop.annotation.Configuration;
import com.litongjava.jfinal.plugin.redis.Redis;
import com.litongjava.jfinal.plugin.redis.RedisPlugin;

@Configuration
public class RedisPluginConfig {

  @Bean(destroyMethod = "stop")
  public RedisPlugin redisPlugin() {
    // 用于缓存bbs模块的redis服务
    RedisPlugin bbsRedis = new RedisPlugin("bbs", "localhost");
    bbsRedis.start();
    // 测试连接
    Redis.use("bbs").getJedis().connect();
    return bbsRedis;
  }
}
