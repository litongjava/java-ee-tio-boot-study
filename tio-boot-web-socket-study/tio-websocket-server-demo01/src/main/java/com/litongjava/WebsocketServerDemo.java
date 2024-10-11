package com.litongjava;

import java.io.IOException;

import com.litongjava.tio.server.ServerTioConfig;
import com.litongjava.tio.websocket.server.WsServerStarter;

public class WebsocketServerDemo {

  public static void main(String[] args) {

    long start = System.currentTimeMillis();
    HelloWebSocketHandler helloWebSocketHandler = new HelloWebSocketHandler();

    WsServerStarter wsServerStarter = null;
    try {
      wsServerStarter = new WsServerStarter(Const.SERVER_PORT, helloWebSocketHandler);
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }

    ServerTioConfig serverTioConfig = wsServerStarter.getServerTioConfig();
    serverTioConfig.setName(Const.APP_NEM);
    serverTioConfig.setServerAioListener(new MyServerAioListener());

    // 设置ip监控
    serverTioConfig.setIpStatListener(new MyIpStatListener());

    // 设置心跳超时时间
    serverTioConfig.setHeartbeatTimeout(Const.HEARTBEAT_TIMEOUT);

    // 如果你希望通过wss来访问，就加上下面的代码吧，不过首先你得有SSL证书（证书必须和域名相匹配，否则可能访问不了ssl）
    // String keyStoreFile = "classpath:config/ssl/keystore.jks";
    // String trustStoreFile = "classpath:config/ssl/keystore.jks";
    // String keyStorePwd = "214323428310224";

    // String keyStoreFile = EnvUtils.get("ssl.keystore", null);
    // String trustStoreFile = EnvUtils.get("ssl.truststore", null);
    // String keyStorePwd = EnvUtils.get("ssl.pwd", null);
    // serverTioConfig.useSsl(keyStoreFile, trustStoreFile, keyStorePwd);

    // 初始化配置
    serverTioConfig.init();
    // 设置ip统计时间段
    serverTioConfig.ipStats.addDurations(Const.IpStatDuration.IPSTAT_DURATIONS);
    try {
      wsServerStarter.start();
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }

    long end = System.currentTimeMillis();
    System.out.println((end - start) + "(ms)");
  }

}