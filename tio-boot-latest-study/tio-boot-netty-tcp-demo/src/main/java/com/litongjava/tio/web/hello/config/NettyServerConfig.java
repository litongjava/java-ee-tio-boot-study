package com.litongjava.tio.web.hello.config;

import com.litongjava.jfinal.aop.annotation.AConfiguration;
import com.litongjava.jfinal.aop.annotation.AInitialization;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.utils.environment.EnvUtils;
import com.litongjava.tio.utils.thread.ThreadUtils;
import com.litongjava.tio.web.hello.nettyserver.NettyChannelHandler;
import com.litongjava.tio.web.hello.nettyserver.NettyServerBootstrap;

@AConfiguration
public class NettyServerConfig {

  @AInitialization(priority = 101)
  public void nettyServerBootstrap() {
    int nioPort = EnvUtils.getInt("nio.server.port", 17902);

    NettyChannelHandler nettyChannelHandler = new NettyChannelHandler();
    NettyServerBootstrap nettyServerBootstrap = new NettyServerBootstrap(nioPort, nettyChannelHandler);

    // start netty
    ThreadUtils.getFixedThreadPool().execute(() -> {
      nettyServerBootstrap.start();
    });

    TioBootServer.me().addDestroyMethod(nettyServerBootstrap::close);
  }
}
