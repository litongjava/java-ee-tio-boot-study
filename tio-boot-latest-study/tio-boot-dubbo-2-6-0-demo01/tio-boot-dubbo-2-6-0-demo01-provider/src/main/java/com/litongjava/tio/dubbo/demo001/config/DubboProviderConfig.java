package com.litongjava.tio.dubbo.demo001.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.qos.common.Constants;
import com.alibaba.dubbo.qos.server.Server;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.jfinal.aop.annotation.AConfiguration;
import com.litongjava.jfinal.aop.annotation.AInitialization;
import com.litongjava.jfinal.dubbo.DubboProvider;
import com.litongjava.tio.boot.constatns.TioBootConfigKeys;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.dubbo.demo001.service.HelloService;
import com.litongjava.tio.dubbo.demo001.service.HiService;
import com.litongjava.tio.dubbo.demo001.service.impl.HelloServiceImpl;
import com.litongjava.tio.dubbo.demo001.service.impl.HiServiceImpl;
import com.litongjava.tio.utils.environment.EnvUtils;
import com.litongjava.tio.utils.thread.TioThreadUtils;

@AConfiguration
public class DubboProviderConfig {

  @AInitialization
  public void config() {

    // 防止qos端口冲突
    System.setProperty("dubbo.application.logger", "slf4j");
    // 防止qos端口冲突
    System.setProperty(Constants.QOS_PORT, 2223 + "");
    // 配置dubbo.qos.accept.foreign.ip是否关闭远程连接
    System.setProperty(Constants.ACCEPT_FOREIGN_IP, "false");

    // 创建应用配置
    ApplicationConfig applicationConfig = new ApplicationConfig();
    applicationConfig.setName(EnvUtils.get(TioBootConfigKeys.APP_NAME));

    // 创建注册中心配置
    RegistryConfig registryConfig = new RegistryConfig();
    registryConfig.setAddress(EnvUtils.get(TioBootConfigKeys.ZOOKEEPER_URL));

    // 创建服务提供者配置
    ProviderConfig providerConfig = new ProviderConfig();

    // init
    DubboProvider.init(applicationConfig, registryConfig, providerConfig);

    // add
    DubboProvider.add(HelloService.class, Aop.get(HelloServiceImpl.class));
    DubboProvider.add(HiService.class, Aop.get(HiServiceImpl.class));

    // export
    TioThreadUtils.submit(() -> {
      DubboProvider.export();
      // 关闭QOS服务 启动后关闭qos服务
      Server.getInstance().stop();
    });

    // unexport
    TioBootServer.me().addDestroyMethod(() -> {
      DubboProvider.unexport();
    });
  }
}
