package com.litongjava.tio.web.hello.config;

import java.util.Collection;

import org.redisson.api.RedissonClient;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.jfinal.aop.annotation.Bean;
import com.litongjava.jfinal.aop.annotation.Configuration;
import com.litongjava.tio.utils.cache.CacheName;
import com.litongjava.tio.utils.cache.CacheNameService;
import com.litongjava.tio.utils.cache.caffeineredis.CaffeineRedisCacheFactory;
import com.litongjava.tio.utils.time.Time;

@Configuration
public class CacheNameConfig {

  @Bean
  public CacheNameService register() {
    //设置CacheName
    CacheName demo = new CacheName("demo", null, Time.MINUTE_1 * 10);
    //将CacheName添加到CacheNameService
    CacheNameService cacheNameService = new CacheNameService();
    cacheNameService.add(demo);

    //将redissonClient添加到CaffeineRedisCacheFactory
    RedissonClient redissonClient = Aop.get(RedissonClient.class);
    CaffeineRedisCacheFactory.INSTANCE.init(redissonClient);
    
    //注册cacheName
    Collection<CacheName> names = cacheNameService.cacheNames();
    for (CacheName cacheName : names) {
      //CaffeineCacheFactory.INSTANCE.register(cacheName);
      //RedisCacheFactory.INSTANCE.register(cacheName);
      CaffeineRedisCacheFactory.INSTANCE.register(cacheName);
    }
    return cacheNameService;
  }
}
