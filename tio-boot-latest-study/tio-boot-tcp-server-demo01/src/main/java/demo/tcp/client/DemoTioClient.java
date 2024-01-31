package demo.tcp.client;

import java.io.IOException;

import com.litongjava.tio.client.ClientChannelContext;
import com.litongjava.tio.client.ClientTioConfig;
import com.litongjava.tio.client.ReconnConf;
import com.litongjava.tio.client.TioClient;
import com.litongjava.tio.client.intf.ClientAioHandler;
import com.litongjava.tio.client.intf.ClientAioListener;
import com.litongjava.tio.core.Node;
import com.litongjava.tio.core.Tio;

import demo.tcp.server.DemoPacket;

public class DemoTioClient {
  /**
   * 启动程序
   */
  public static void main(String[] args) throws Exception {

    // 服务器节点
    Node serverNode = new Node("127.0.0.1", 9998);
    // handler,包含编解码,消息处理
    ClientAioHandler clientAioHandler = new DemoClientAioHandler();
    // 初始化
    ClientChannelContext clientChannelContext = init(serverNode, clientAioHandler);
    // 发送消息
    send(clientChannelContext, "Hello,World");
  }

  public static ClientChannelContext init(Node serverNode, ClientAioHandler clientAioHandler)
      throws IOException, Exception {

    // 事件监听器,可以为null,但是建议自己实现该接口,可以参考showcase
    ClientAioListener clientAioListener = null;

    // 断链后自动连接,不自动连接设置为null
    ReconnConf reconnConf = new ReconnConf(50000L);
    // 共用上下文对象
    ClientTioConfig clientTioConfig = new ClientTioConfig(clientAioHandler, clientAioListener, reconnConf);

    // 发送消息客户端
    TioClient tioClient;
    // 客户端通道上下文,连接服务器后获得
    ClientChannelContext clientChannelContext;

    // 设置心跳时间
    clientTioConfig.setHeartbeatTimeout(0);
    // 初始化client
    tioClient = new TioClient(clientTioConfig);
    // 连接服务器
    clientChannelContext = tioClient.connect(serverNode);
    return clientChannelContext;
  }

  private static void send(ClientChannelContext clientChannelContext, String message) {
    DemoPacket helloPacket = new DemoPacket();
    helloPacket.setBody(message.getBytes());
    Tio.send(clientChannelContext, helloPacket);
  }
}