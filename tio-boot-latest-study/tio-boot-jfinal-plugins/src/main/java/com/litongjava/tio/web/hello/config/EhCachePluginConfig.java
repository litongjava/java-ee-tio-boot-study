package com.litongjava.tio.web.hello.config;

import com.litongjava.jfinal.aop.annotation.Bean;
import com.litongjava.jfinal.aop.annotation.Configuration;
import com.litongjava.jfinal.plugin.ehcache.EhCachePlugin;

@Configuration
public class EhCachePluginConfig {

  @Bean(destroyMethod = "stop")
  public EhCachePlugin ehCachePlugin() {
    EhCachePlugin ehCachePlugin = new EhCachePlugin();
    ehCachePlugin.start();
    return ehCachePlugin;
  }
}
