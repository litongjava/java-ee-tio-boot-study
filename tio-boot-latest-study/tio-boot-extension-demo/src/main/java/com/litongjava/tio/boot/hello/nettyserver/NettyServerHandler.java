package com.litongjava.tio.boot.hello.nettyserver;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    // 出现异常就关闭
    cause.printStackTrace();
    ctx.close();
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    try {
      ByteBuf in = (ByteBuf) msg;
      String string = in.toString(CharsetUtil.UTF_8);
      log.info("received:{}", string);
      // 这里调用service服务,数据库
    } finally {
      ReferenceCountUtil.release(msg);
    }
  }
}
