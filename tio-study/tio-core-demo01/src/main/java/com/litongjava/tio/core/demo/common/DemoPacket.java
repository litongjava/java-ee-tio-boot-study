package com.litongjava.tio.core.demo.common;
import com.litongjava.tio.core.intf.Packet;

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