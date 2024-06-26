package com.litongjava.tio.web.hello.config;

import com.litongjava.jfinal.aop.annotation.AInitialization;
import com.litongjava.jfinal.aop.annotation.BeforeStartConfiguration;
import com.litongjava.jfinal.plugin.redis.Cache;
import com.litongjava.jfinal.plugin.redis.Redis;
import com.litongjava.jfinal.plugin.redis.RedisPlugin;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.utils.environment.EnvironmentUtils;

@BeforeStartConfiguration
public class RedisPluginConfig {

  @AInitialization(priority = 99)
  public RedisPlugin redisPlugin() {
    String host = EnvironmentUtils.getStr("redis.host");
    Integer port = EnvironmentUtils.getInt("redis.port");
    String password = EnvironmentUtils.getStr("redis.password");
    String cacheName = EnvironmentUtils.get("redis.cacheName");

    // 创建并启动 Redis 插件
    RedisPlugin mainRedis = new RedisPlugin(cacheName, host, port, password);
    mainRedis.start();

    // 测试连接
    Cache cache = Redis.use();
    cache.getJedis().connect();

    TioBootServer.me().addDestroyMethod(mainRedis::stop);
    return mainRedis;
  }
}
