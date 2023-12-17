package com.litongjava.tio.boot.hello.tcp;

import java.nio.ByteBuffer;

import com.litongjava.tio.boot.tcp.ServerTcpHandler;
import com.litongjava.tio.core.ChannelContext;
import com.litongjava.tio.core.Tio;
import com.litongjava.tio.core.TioConfig;
import com.litongjava.tio.core.exception.TioDecodeException;
import com.litongjava.tio.core.intf.Packet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoHandler implements ServerTcpHandler {

  public Packet decode(ByteBuffer buffer, int limit, int position, int readableLength, ChannelContext channelContext)
      throws TioDecodeException {
    log.info("buffer:{}", buffer);
    // 获取由ByteBuffer支持的字节数组
    byte[] bytes = new byte[readableLength];
    buffer.get(bytes);
    // 封装为ShowcasePacket
    DemoPacket imPackage = new DemoPacket();
    imPackage.setBody(bytes);
    return imPackage;
  }

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

  public void handler(Packet packet, ChannelContext channelContext) throws Exception {
    DemoPacket packingPacket = (DemoPacket) packet;
    byte[] body = packingPacket.getBody();
    if (body == null) {
      return;
    }
    String string = new String(body);
    log.info("received:{}", string);
    // 响应数据
    String sendMessage = "echo:" + string;
    log.info("sendMessage:{}", sendMessage);
    byte[] bytes = sendMessage.getBytes();
    // 响应包
    DemoPacket responsePacket = new DemoPacket();
    responsePacket.setBody(bytes);
    // 响应消息
    log.info("开始响应");
    Tio.send(channelContext, responsePacket);
    log.info("响应完成");
  }
}
