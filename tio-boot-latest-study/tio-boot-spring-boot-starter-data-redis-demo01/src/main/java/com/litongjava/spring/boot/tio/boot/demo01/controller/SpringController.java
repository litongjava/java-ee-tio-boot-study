package com.litongjava.spring.boot.tio.boot.demo01.controller;

import org.springframework.data.redis.core.StringRedisTemplate;

import com.litongjava.jfinal.spring.SpringBeanContextUtils;
import com.litongjava.tio.http.server.annotation.RequestPath;

@RequestPath("/spring")
public class SpringController {

  public String[] beans() {
    return SpringBeanContextUtils.getContext().getBeanDefinitionNames();
  }

  public String redisTest() {
    StringRedisTemplate redisTemplate = SpringBeanContextUtils.getBean(StringRedisTemplate.class);
    redisTemplate.opsForValue().set("key1", "value1");
    return redisTemplate.opsForValue().get("key1");

  }
}
