package com.litongjava.tio.boot.hello.tioserver;

import java.io.IOException;

import com.litongjava.tio.server.ServerTioConfig;
import com.litongjava.tio.server.TioServer;
import com.litongjava.tio.server.intf.ServerAioHandler;
import com.litongjava.tio.server.intf.ServerAioListener;

public class DemoTioServer {
  // handler, 包括编码、解码、消息处理
  ServerAioHandler serverHandler = new DemoTioServerHandler();
  // 事件监听器，可以为null，但建议自己实现该接口，可以参考showcase了解些接口
  ServerAioListener serverListener = new DemoTioServerListener();
  // 配置对象
  ServerTioConfig tioServerConfig = new ServerTioConfig(serverHandler, serverListener);

  /**
   * 启动程序入口
   */
  public void start() throws IOException {

    // 设置心跳,-1 取消心跳
    tioServerConfig.setHeartbeatTimeout(-1);
    // TioServer对象
    TioServer tioServer = new TioServer(tioServerConfig);

    // 启动服务
    tioServer.start(null, 6789);
  }
}