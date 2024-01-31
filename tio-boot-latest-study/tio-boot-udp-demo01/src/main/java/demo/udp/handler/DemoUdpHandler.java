package demo.udp.handler;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

import com.litongjava.tio.core.Node;
import com.litongjava.tio.core.udp.UdpPacket;
import com.litongjava.tio.core.udp.intf.UdpHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoUdpHandler implements UdpHandler {
  /**
   * 处理udp消息
   */
  public void handler(UdpPacket udpPacket, DatagramSocket datagramSocket) {
    byte[] data = udpPacket.getData();
    String msg = new String(data);
    Node remote = udpPacket.getRemote();

    log.info("收到来自{}的消息:【{}】", remote, msg);
    DatagramPacket datagramPacket = new DatagramPacket(data, data.length,
        new InetSocketAddress(remote.getIp(), remote.getPort()));
    try {
      datagramSocket.send(datagramPacket);
    } catch (Throwable e) {
      log.error(e.toString(), e);
    }
  }

}
