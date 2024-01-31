package demo.tcp.client;

import java.nio.ByteBuffer;

import com.litongjava.tio.client.intf.ClientAioHandler;
import com.litongjava.tio.core.ChannelContext;
import com.litongjava.tio.core.TioConfig;
import com.litongjava.tio.core.exception.TioDecodeException;
import com.litongjava.tio.core.intf.Packet;

import demo.tcp.server.DemoPacket;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoClientAioHandler implements ClientAioHandler {

  /**
   * 解码:把接收到的ByteBuffer解码成应用可以识别的业务消息包
   */
  @Override
  public Packet decode(ByteBuffer buffer, int limit, int position, int readableLength, ChannelContext var5)
      throws TioDecodeException {
    log.info("buffer:{}", buffer);
    // 转换前准备ByteBuffer
    int length = buffer.remaining();
    // 获取由ByteBuffer支持的字节数组
    byte[] bytes = new byte[length];
    buffer.get(bytes);
    // 封装为DemoPacket
    DemoPacket imPackage = new DemoPacket();
    imPackage.setBody(bytes);
    return imPackage;

  }

  /**
   * 编码:把业务消息包编码为可以发送的ByteBuffer
   */
  @Override
  public ByteBuffer encode(Packet packet, TioConfig tioConfig, ChannelContext chanelContext) {

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
  @Override
  public void handler(Packet packet, ChannelContext var2) throws Exception {
    log.info("handler");
    DemoPacket helloPacket = (DemoPacket) packet;
    byte[] body = helloPacket.getBody();
    if (body != null) {
      String str = new String(body);
      System.out.println("received::" + str);
    }
  }

  /**
   * 此方法如果返回null,框架层面则不会发出心跳,如果返回非null,框架层面会定时发送本方法返回的消息包
   */
  @Override
  public Packet heartbeatPacket(ChannelContext var1) {
    return null;
  }

}
