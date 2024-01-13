package com.litongjava.tio.web.hello;

import com.litongjava.jfinal.aop.annotation.ABean;
import com.litongjava.jfinal.aop.annotation.AConfiguration;
import com.litongjava.jfinal.plugin.redis.Cache;
import com.litongjava.jfinal.plugin.redis.Redis;
import com.litongjava.jfinal.plugin.redis.RedisPlugin;

@AConfiguration
public class RedisPluginConfig {

  @ABean(destroyMethod = "stop")
  public RedisPlugin redisPlugin() {
    RedisPlugin bbsRedis = new RedisPlugin("main", "localhost");
    bbsRedis.start();
    // 测试连接
    Cache cache = Redis.use("main");
    cache.getJedis().connect();
    return bbsRedis;
  }
}
