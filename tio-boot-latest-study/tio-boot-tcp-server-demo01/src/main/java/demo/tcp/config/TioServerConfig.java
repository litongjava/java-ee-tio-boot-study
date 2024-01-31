package demo.tcp.config;

import java.io.IOException;

import com.litongjava.jfinal.aop.annotation.AConfiguration;
import com.litongjava.jfinal.aop.annotation.AInitialization;
import com.litongjava.tio.server.ServerTioConfig;
import com.litongjava.tio.server.TioServer;
import com.litongjava.tio.server.intf.ServerAioHandler;
import com.litongjava.tio.server.intf.ServerAioListener;

import demo.tcp.server.DemoTioServerHandler;
import demo.tcp.server.DemoTioServerListener;

@AConfiguration
public class TioServerConfig {

  @AInitialization
  public void demoTioServer() {
    // handler, 包括编码、解码、消息处理
    ServerAioHandler serverHandler = new DemoTioServerHandler();
    // 事件监听器，可以为null，但建议自己实现该接口，可以参考showcase了解些接口
    ServerAioListener serverListener = new DemoTioServerListener();
    // 配置对象
    ServerTioConfig tioServerConfig = new ServerTioConfig("tcp-server", serverHandler, serverListener);

    // 设置心跳,-1 取消心跳
    tioServerConfig.setHeartbeatTimeout(-1);
    // TioServer对象
    TioServer tioServer = new TioServer(tioServerConfig);

    // 启动服务
    try {
      tioServer.start(null, 9998);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}