package com.litongjava.tio.boot.hello.nettyserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyServerBootstrap {
  private int port;
  private NettyChannelHandler nettyChannelHandler;
  private EventLoopGroup boss = new NioEventLoopGroup();
  private EventLoopGroup worker = new NioEventLoopGroup();

  public NettyServerBootstrap(int port, NettyChannelHandler nettyChannelHandler) {
    this.port = port;
    this.nettyChannelHandler = nettyChannelHandler;
  }

  public void start() {
    boss = new NioEventLoopGroup();
    worker = new NioEventLoopGroup();
    try {
      ServerBootstrap bootstrap = new ServerBootstrap();
      bootstrap.group(boss, worker);
      bootstrap.channel(NioServerSocketChannel.class);
      bootstrap.option(ChannelOption.SO_BACKLOG, 1024); // 连接数
      bootstrap.option(ChannelOption.TCP_NODELAY, true); // 不延迟，消息立即发送
      bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true); // 长连接
      bootstrap.childHandler(nettyChannelHandler);
      ChannelFuture f = bootstrap.bind(port).sync();
      if (f.isSuccess()) {
        log.info("netty start successful:{}", this.port);
      }
      f.channel().closeFuture().sync();
    } catch (Exception e) {
      log.info("netty start fail：" + e.getMessage());
      e.printStackTrace();
    } finally {
      close();
    }
  }

  public void close() {
    log.info("close netty");
    if (boss != null) {
      boss.shutdownGracefully();
    }
    if (worker != null) {
      worker.shutdownGracefully();
    }

  }
}