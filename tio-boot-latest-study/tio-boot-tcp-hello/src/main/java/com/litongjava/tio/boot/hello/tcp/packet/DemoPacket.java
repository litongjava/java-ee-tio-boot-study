package com.litongjava.tio.boot.hello.tcp.packet;

import com.litongjava.aio.Packet;

/**
* socket消息包
*/
@SuppressWarnings("serial")
public class DemoPacket extends Packet {
  private byte[] body;

  public byte[] getBody() {
    return body;
  }

  public void setBody(byte[] body) {
    this.body = body;
  }
}