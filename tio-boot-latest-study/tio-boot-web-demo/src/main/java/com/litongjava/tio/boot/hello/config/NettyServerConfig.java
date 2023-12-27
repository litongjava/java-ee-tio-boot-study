package com.litongjava.tio.boot.hello.config;

import com.litongjava.jfinal.aop.annotation.Bean;
import com.litongjava.jfinal.aop.annotation.Configuration;
import com.litongjava.tio.boot.hello.nettyserver.NettyChannelHandler;
import com.litongjava.tio.boot.hello.nettyserver.NettyServerBootstrap;
import com.litongjava.tio.utils.environment.EnvironmentUtils;

import cn.hutool.core.thread.ThreadUtil;

@Configuration
public class NettyServerConfig {

  @Bean(destroyMethod = "close")
  public NettyServerBootstrap nettyServerBootstrap() {
    int nioPort = EnvironmentUtils.getInt("noi.server.port", 17902);

    NettyChannelHandler nettyChannelHandler = new NettyChannelHandler();
    NettyServerBootstrap nettyServerBootstrap = new NettyServerBootstrap(nioPort, nettyChannelHandler);
    ThreadUtil.execute(() -> {
      nettyServerBootstrap.start();
    });
    return nettyServerBootstrap;
  }
}
