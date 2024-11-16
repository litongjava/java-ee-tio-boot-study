package com.litongjava.tio.web.hello.handler;

import java.nio.ByteBuffer;

import com.litongjava.tio.boot.decode.TioDecodeExceptionHandler;
import com.litongjava.tio.core.ChannelContext;
import com.litongjava.tio.core.exception.TioDecodeException;
import com.litongjava.tio.http.common.HttpConfig;

public class MyDecodeExceptionHandler implements TioDecodeExceptionHandler {

  @Override
  public void handle(ByteBuffer buffer, ChannelContext channelContext, HttpConfig httpConfig, TioDecodeException e) {
    // 创建缓冲区的只读副本
    ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();

    // 将位置重置为0，以从缓冲区的起始位置读取数据
    readOnlyBuffer.position(0);

    try {
      if (readOnlyBuffer.hasRemaining()) {
        byte[] bytes = new byte[readOnlyBuffer.remaining()];
        readOnlyBuffer.get(bytes);
        System.out.println("Buffer content: " + new String(bytes));
      } else {
        System.out.println("Buffer is empty.");
      }
      System.out.println("TioDecodeException: " + e.toString());
    } catch (Exception ex) {
      System.err.println("Error while reading buffer: " + ex.getMessage());
    }
  }
}
