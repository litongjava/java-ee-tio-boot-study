package com.litongjava.tio.web.hello.config;

import com.litongjava.hotswap.kit.HotSwapUtils;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.jfinal.aop.annotation.BeforeStartConfiguration;
import com.litongjava.jfinal.aop.annotation.Initialization;
import com.litongjava.tio.boot.constatns.ConfigKeys;
import com.litongjava.tio.boot.utils.Enviorment;

import lombok.extern.slf4j.Slf4j;

@BeforeStartConfiguration
@Slf4j
public class HowSwapClassLoaderConfig {
  @Initialization
  public void configClassLoader() {
    Enviorment enviorment = Aop.get(Enviorment.class);
    String env = enviorment.get(ConfigKeys.appEnv);
    if ("dev".equals(env)) {
      // 获取自定义的classLoalder
      ClassLoader hotSwapClassLoader = HotSwapUtils.getClassLoader();
      Thread.currentThread().setContextClassLoader(hotSwapClassLoader);
      log.info("hotSwapClassLoader:{}", hotSwapClassLoader);
    }
  }
}
