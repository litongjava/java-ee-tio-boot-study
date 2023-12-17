package com.litongjava.tio.boot.hello.controller;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.jfinal.aop.Autowired;
import com.litongjava.jfinal.aop.annotation.Controller;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.server.annotation.RequestPath;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

@Controller
@RequestPath("/redisson")
@Slf4j
public class RedissonTestController {
  @RequestPath("/test")
  public String test(HttpRequest request) {
    String value = null;

    // 使用 Redisson 客户端
    RedissonClient redissonClient = Aop.get(RedissonClient.class);
    // 例如，获取一个锁
    RLock lock = redissonClient.getLock("myLock");
    lock.lock();
    // 创建或获取一个存储桶对象
    RBucket<String> bucket = redissonClient.getBucket("yourKey");
    //获取支持
    value = bucket.get();
    if (value == null) {

      log.info("计算新的value");
      value = "yourValue";
      // 向 Redis 中设置值
      bucket.set(value);
    }

    try {
      // 处理你的业务逻辑
    } finally {
      lock.unlock();
    }
    return value;
  }
}