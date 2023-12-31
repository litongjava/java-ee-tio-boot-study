//package com.litongjava.tio.boot.hello.config;
//
//import cn.hutool.db.nosql.redis.RedisDS;
//import cn.hutool.setting.Setting;
//import com.jfinal.kit.StrKit;
//import com.litongjava.jfinal.aop.Aop;
//import com.litongjava.jfinal.aop.annotation.Bean;
//import com.litongjava.jfinal.aop.annotation.Configuration;
//import com.litongjava.tio.boot.context.Enviorment;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//import redis.clients.jedis.Protocol;
//
//@Configuration
//public class HutoolRedisConfig {
//  @Bean(destroyMethod = "close")
//  public RedisDS redisDS() {
//    Enviorment enviorment = Aop.get(Enviorment.class);
//    String redisHost = enviorment.get("redis.host", "127.0.0.1");
//    String redisPort = enviorment.get("redis.port", "6379");
//    String redisPassword = enviorment.get("redis.password");
//
//    // 配置你的Redis连接信息
//    Setting setting = new Setting();
//
//    String group = "redis";
//    // 地址
//    setting.setByGroup("host", group, redisHost);
//    // 端口
//    setting.setByGroup("port", group, redisPort);
//    // 密码
//    if (!StrKit.isBlank(redisPassword)) {
//      setting.setByGroup("password", group, redisPassword);
//    }
//
//    // 连接超时
//    setting.setByGroup("timeout", group, String.valueOf(Protocol.DEFAULT_TIMEOUT));
//    setting.setByGroup("connectionTimeout", group, String.valueOf(Protocol.DEFAULT_TIMEOUT));
//    // 读取数据超时
//    setting.setByGroup("timeout", group, String.valueOf(Protocol.DEFAULT_TIMEOUT));
//    setting.setByGroup("soTimeout", group, String.valueOf(Protocol.DEFAULT_TIMEOUT));
//    // 数据库序号
//    setting.setByGroup("database", group, String.valueOf(Protocol.DEFAULT_DATABASE));
//    // 客户端名
//    setting.setByGroup("clientName", group, "Hutool");
//    // 是否使用SSL
//    setting.setByGroup("ssl", group, String.valueOf(false));
//    RedisDS redisDS = new RedisDS(setting, group);
//    //连接redis
//    redisDS.getJedis();
//    return redisDS;
//  }
//}