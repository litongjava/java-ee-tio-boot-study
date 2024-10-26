package com.litongjava.tio.web.hello.config;

import com.litongjava.annotation.BeforeStartConfiguration;
import com.litongjava.annotation.Initialization;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.utils.cache.redismap.RedisMapCacheFactory;
import com.litongjava.tio.utils.environment.EnvUtils;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@BeforeStartConfiguration
public class BeforeTioStartConfig {

  @Initialization
  public void config() {
    String host = EnvUtils.getStr("redis.host");
    int port = EnvUtils.getInt("redis.port", 6379);
    Integer timeout = EnvUtils.getInt("redis.timeout", 2000);
    String password = EnvUtils.getStr("redis.password");
    int database = EnvUtils.getInt("redis.database", 0);
    JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), host, port, timeout, password, database);
    RedisMapCacheFactory.INSTANCE.setJedisPool(jedisPool);
    TioBootServer.me().addDestroyMethod(jedisPool::close);
  }
}
