package demo.udp.config;

import java.net.SocketException;

import com.litongjava.jfinal.aop.annotation.AConfiguration;
import com.litongjava.jfinal.aop.annotation.AInitialization;
import com.litongjava.tio.core.udp.UdpServer;
import com.litongjava.tio.core.udp.UdpServerConf;

import demo.udp.handler.DemoUdpHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AConfiguration
public class UdpServerConfig {

  @AInitialization
  public void config() {
    DemoUdpHandler fpmsUdpHandler = new DemoUdpHandler();
    UdpServerConf udpServerConf = new UdpServerConf(3000, fpmsUdpHandler, 5000);
    UdpServer udpServer;
    try {
      udpServer = new UdpServer(udpServerConf);
      udpServer.start();
      log.info("udp started");
    } catch (SocketException e) {
      e.printStackTrace();
    }

  }
}
