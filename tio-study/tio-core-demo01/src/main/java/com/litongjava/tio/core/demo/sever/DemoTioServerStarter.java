package com.litongjava.tio.core.demo.sever;

import java.io.IOException;

import com.litongjava.tio.core.stat.IpStatListener;
import com.litongjava.tio.server.ServerTioConfig;
import com.litongjava.tio.server.TioServer;
import com.litongjava.tio.server.intf.ServerAioHandler;
import com.litongjava.tio.server.intf.ServerAioListener;

public class DemoTioServerStarter {
  /**
   * 启动程序入口
   */
  public static void main(String[] args) throws IOException {
    // handler, 包括编码、解码、消息处理
    ServerAioHandler serverHandler = new DemoTioServerHandler();
    // 事件监听器，可以为null，但建议自己实现该接口，可以参考showcase了解些接口
    ServerAioListener serverListener = new DemoTioServerListener();
    // 配置对象
    ServerTioConfig tioServerConfig = new ServerTioConfig("tio-server", serverHandler, serverListener);
    // 设置心跳,-1 取消心跳
    tioServerConfig.setHeartbeatTimeout(1000);
    //tioServerConfig.setHeartbeatTimeout(0);
    tioServerConfig.debug = true;
    IpStatListener ipStatListener = tioServerConfig.getIpStatListener();
    System.out.println(ipStatListener);
    // TioServer对象
    TioServer tioServer = new TioServer(tioServerConfig);

    // 启动服务
    tioServer.start(null, 6789);
  }
}