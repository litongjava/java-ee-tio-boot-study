package com.litongjava.spring.boot.tio.boot.demo01.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.litongjava.annotation.AConfiguration;
import com.litongjava.annotation.Initialization;
import com.litongjava.hook.HookCan;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.jfinal.aop.AopManager;
import com.litongjava.jfinal.spring.SpringBeanContextUtils;
import com.litongjava.spring.boot.tio.boot.demo01.Applicaton;
import com.litongjava.tio.utils.environment.EnvUtils;

@AConfiguration
public class SpringConfig {

  @Initialization
  public void config() {
    // 启动spring-boot
    ConfigurableApplicationContext context = SpringApplication.run(Applicaton.class, EnvUtils.getArgs());

    // 开启和spring的整合
    AopManager.me().setEnableWithSpring(true);
    SpringBeanContextUtils.setContext(context);
    // 让 tio-boot的bean支持Autowired注解
    Aop.addFetchBeanAnnotation(Autowired.class);

    HookCan.me().addDestroyMethod(() -> {
      context.close();
    });
  }
}
