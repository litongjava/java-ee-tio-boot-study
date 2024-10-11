package com.litongjava;

import java.util.Objects;

import com.litongjava.tio.core.ChannelContext;
import com.litongjava.tio.core.Tio;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.websocket.common.WebSocketRequest;
import com.litongjava.tio.websocket.common.WebSocketResponse;
import com.litongjava.tio.websocket.common.WebSocketSessionContext;
import com.litongjava.tio.websocket.server.handler.IWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloWebSocketHandler implements IWebSocketHandler {
  /**
   * 握手成功后执行，绑定群组并通知其他用户
   */
  public HttpResponse handshake(HttpRequest request, HttpResponse response, ChannelContext channelContext) throws Exception {
    String clientIp = request.getClientIp();
    String name = request.getParam("name");

    Tio.bindUser(channelContext, name);
    log.info("收到来自 {} 的 WebSocket 握手请求：{}", clientIp, request.toString());
    return response;
  }

  /**
   * 处理文本消息，并进行消息广播
   */
  public void onAfterHandshaked(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {

    // 绑定到群组，便于消息广播
    Tio.bindGroup(channelContext, Const.GROUP_ID);
    // 获取当前在线用户数量
    int count = Tio.getAll(channelContext.tioConfig).getObj().size();

    String message = "{name:'admin',message:'" + channelContext.userid + " 进入聊天室，当前在线人数：" + count + "'}";
    WebSocketResponse wsResponse = WebSocketResponse.fromText(message, Const.CHARSET);
    // 广播消息给群组内所有用户
    Tio.sendToGroup(channelContext.tioConfig, Const.GROUP_ID, wsResponse);
  }

  /**
   * 处理二进制消息
   */
  public Object onBytes(WebSocketRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
    return null;
  }

  /**
   * 处理连接关闭请求，进行资源清理
   */
  public Object onClose(WebSocketRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
    Tio.remove(channelContext, "客户端主动关闭连接");
    return null;
  }

  /**
   * 处理文本消息
   */
  public Object onText(WebSocketRequest wsRequest, String text, ChannelContext channelContext) throws Exception {
    WebSocketSessionContext wsSessionContext = (WebSocketSessionContext) channelContext.get();
    String path = wsSessionContext.getHandshakeRequest().getRequestLine().path;
    log.info("路径：{}，收到消息：{}", path, text);

    if (Objects.equals("心跳内容", text)) {
      return null; // 忽略心跳消息
    }

    String message = "{name:'" + channelContext.userid + "',message:'" + text + "'}";
    WebSocketResponse wsResponse = WebSocketResponse.fromText(message, Const.CHARSET);
    // 广播消息给群组内所有用户
    Tio.sendToGroup(channelContext.tioConfig, Const.GROUP_ID, wsResponse);

    return null; // 不需要额外的返回值
  }

}
