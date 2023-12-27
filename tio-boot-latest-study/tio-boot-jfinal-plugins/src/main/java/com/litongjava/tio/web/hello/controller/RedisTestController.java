package com.litongjava.tio.web.hello.controller;

import com.alibaba.fastjson2.JSON;
import com.litongjava.jfinal.plugin.redis.Cache;
import com.litongjava.jfinal.plugin.redis.Redis;
import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.web.hello.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestPath("/redis/test")
public class RedisTestController {

  /**
   * 测试redis
   * @return
   */
  public Object test01() {
    String cacheKey = "litong";

    Cache bbsCache = Redis.use("bbs");
    Object value = bbsCache.get(cacheKey);
    if (value == null) {
      log.info("计算新的值");
      bbsCache.set(cacheKey, "value___001");
    }
    return value;
  }

  /**
   * 使用Redis.call方法
   * @return
   */
  public User test02() {
    User user = Redis.call(jedis -> {
      String userJsonString = jedis.get("user");
      return JSON.parseObject(userJsonString, User.class);
    });

    if (user == null) {
      log.info("重新计算user");
      User user1 = new User("ping", "00000000");
      user = user1;
      // 或者简化为下面代码
      Redis.call(j -> {
        return j.set("user", JSON.toJSONString(user1));
      });
    }

    return user;

  }

  /**
   * 调用Jedis API
   * @return
   */
  public Long test03() {
    Long ret = Redis.call(j -> j.incrBy("increase", 1));
    return ret;
  }
}
