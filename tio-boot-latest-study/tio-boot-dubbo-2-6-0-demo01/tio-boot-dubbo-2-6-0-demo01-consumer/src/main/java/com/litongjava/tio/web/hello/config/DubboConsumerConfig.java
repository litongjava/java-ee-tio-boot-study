package com.litongjava.tio.web.hello.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.qos.common.Constants;
import com.alibaba.dubbo.qos.server.Server;
import com.litongjava.jfinal.aop.annotation.AConfiguration;
import com.litongjava.jfinal.aop.annotation.AInitialization;
import com.litongjava.jfinal.dubbo.Dubbo;
import com.litongjava.tio.boot.constatns.TioBootConfigKeys;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.dubbo.demo001.service.HelloService;
import com.litongjava.tio.dubbo.demo001.service.HiService;
import com.litongjava.tio.utils.environment.EnvUtils;

@AConfiguration
public class DubboConsumerConfig {

  @AInitialization
  public void config() {
    // 配置日志系统
    System.setProperty("dubbo.application.logger", "slf4j");
    // 防止qos端口冲突
    System.setProperty(Constants.QOS_PORT, 2224 + "");
    // 配置dubbo.qos.accept.foreign.ip是否关闭远程连接
    System.setProperty(Constants.ACCEPT_FOREIGN_IP, "false");

    // 创建应用配置
    ApplicationConfig applicationConfig = new ApplicationConfig();
    applicationConfig.setName(EnvUtils.get(TioBootConfigKeys.APP_NAME));

    // 创建注册中心配置
    RegistryConfig registryConfig = new RegistryConfig();
    registryConfig.setAddress(EnvUtils.get(TioBootConfigKeys.ZOOKEEPER_URL));

    // 设置dubbo
    Dubbo.setApplication(applicationConfig);
    Dubbo.setRegistry(registryConfig);

    // 获取服务器类
    Dubbo.get(HelloService.class);
    Dubbo.get(HiService.class);

    // 关闭QOS服务 启动后关闭qos服务
    Server.getInstance().stop();

    TioBootServer.me().addDestroyMethod(() -> {
      Dubbo.clear();
    });
  }
}