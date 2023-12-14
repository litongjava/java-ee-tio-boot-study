//package com.litongjava.tio.boot.hello.config;
//
//import com.litongjava.jfinal.aop.annotation.Bean;
//import com.litongjava.jfinal.aop.annotation.Configuration;
//import redis.clients.jedis.Jedis;
//
////@Configuration
//public class JedisConfig {
//
//  @Bean(destroyMethod = "close")
//  public Jedis jedis() {
//    //得到Jedis对象
//    Jedis jedis = new Jedis("localhost", 6379);
//    //jedis.auth();
//    //向redis中添加一个字符串,测试中文乱码
//    jedis.set("name", "litong");
//    //获取redis中的字符串
//    String string = jedis.get("name");
//    System.out.println(string);
//    return jedis;
//  }
//}
