package com.litongjava.tio.boot.hello.nettyserver;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class NettyChannelHandler extends ChannelInitializer<SocketChannel> {
  @Override
  protected void initChannel(SocketChannel socketChannel) throws Exception {
    ChannelPipeline p = socketChannel.pipeline();
    p.addLast(new NettyServerHandler());
  }
}