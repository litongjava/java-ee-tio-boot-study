package com.litongjava.tio.web.hello.config;

import com.litongjava.jfinal.aop.annotation.Bean;
import com.litongjava.jfinal.aop.annotation.Configuration;
import com.litongjava.jfinal.plugin.cron4j.Cron4jPlugin;
import com.litongjava.tio.web.hello.task.MyTask;

@Configuration
public class Cron4jPluginConfig {

  @Bean(destroyMethod = "stop")
  public Cron4jPlugin cron4jPlugin() {
    Cron4jPlugin cp = new Cron4jPlugin();
    // 每1分钟执行一次
    cp.addTask("* * * * * ", new MyTask());
    cp.start();
    return cp;
  }
}
