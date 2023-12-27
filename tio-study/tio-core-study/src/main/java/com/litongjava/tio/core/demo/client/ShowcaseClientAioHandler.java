package com.litongjava.tio.core.demo.client;

import java.nio.ByteBuffer;

import com.litongjava.tio.client.intf.ClientAioHandler;
import com.litongjava.tio.core.ChannelContext;
import com.litongjava.tio.core.TioConfig;
import com.litongjava.tio.core.demo.common.DemoPacket;
import com.litongjava.tio.core.exception.TioDecodeException;
import com.litongjava.tio.core.intf.Packet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShowcaseClientAioHandler implements ClientAioHandler {
  /**
   * 解码:把接收到的ByteBuffer解码成应用可以识别的业务消息包
   */
  public Packet decode(ByteBuffer buffer, int limit, int position, int readableLength, ChannelContext channelContext)
      throws TioDecodeException {
    log.info("buffer:{}", buffer);
    // 转换前准备ByteBuffer
    int length = buffer.remaining();
    // 获取由ByteBuffer支持的字节数组
    byte[] bytes = new byte[length];
    buffer.get(bytes);
    // 封装为ShowcasePacket
    DemoPacket imPackage = new DemoPacket();
    imPackage.setBody(bytes);
    return imPackage;
  }

  /**
   * 编码:把业务消息包编码为可以发送的ByteBuffer
   */
  public ByteBuffer encode(Packet packet, TioConfig tioConfig, ChannelContext channelContext) {
    DemoPacket helloPacket = (DemoPacket) packet;
    byte[] body = helloPacket.getBody();
    // ByteBuffer的总长度是消息体长度
    int bodyLength = body.length;
    log.info("encode:{}", bodyLength);

    // 创建一个新的ByteBuffer
    ByteBuffer buffer = ByteBuffer.allocate(bodyLength);
    // 设置字节序

    buffer.order(tioConfig.getByteOrder());
    // 消息消息体
    buffer.put(body);
    return buffer;
  }

  /**
   * 处理消息
   */

  public void handler(Packet packet, ChannelContext channelContext) throws Exception {
    log.info("handler");
    DemoPacket helloPacket = (DemoPacket) packet;
    byte[] body = helloPacket.getBody();
    if (body != null) {
      String str = new String(body);
      System.out.println("收到的消息是:" + str);
    }
  }

  /**
   * 心跳检测包
   * 此方法如果返回null,框架层面则不会发出心跳,如果返回非null,框架层面会定时发送本方法返回的消息包
   * 
   */
  public Packet heartbeatPacket(ChannelContext channelContext) {

    return null;
  }

}