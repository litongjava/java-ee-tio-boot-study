package com.litongjava.tio.web.socket.hello.handler;

import java.util.Objects;

import com.litongjava.tio.core.ChannelContext;
import com.litongjava.tio.core.Tio;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.websocket.common.WsRequest;
import com.litongjava.tio.websocket.common.WsResponse;
import com.litongjava.tio.websocket.common.WsSessionContext;
import com.litongjava.tio.websocket.server.handler.IWsMsgHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloWebSocketHandler implements IWsMsgHandler {

  /**
   * 用于群聊的group id
   */
  public static final String GROUP_ID = "showcase-websocket";
  public static final String CHARSET = "utf-8";

  /**
   * 处理握手，业务可以在这里获取cookie，request参数等
   */
  @Override
  public HttpResponse handshake(HttpRequest request, HttpResponse httpResponse, ChannelContext channelContext)
    throws Exception {
    String clientip = request.getClientIp();
    String myname = request.getParam("name");

    Tio.bindUser(channelContext, myname);
    // channelContext.setUserid(myname);
    log.info("收到来自{}的ws握手包{}", clientip, request.toString());
    return httpResponse;
  }

  /**
   * 握手完成后
   * @param httpRequest
   * @param httpResponse
   * @param channelContext
   * @throws Exception
   * @author tanyaowu
   */
  @Override
  public void onAfterHandshaked(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext)
    throws Exception {
    // 绑定到群组，后面会有群发
    Tio.bindGroup(channelContext, GROUP_ID);
    int count = Tio.getAll(channelContext.tioConfig).getObj().size();

    String msg = "{name:'admin',message:'" + channelContext.userid + " 进来了，共【" + count + "】人在线" + "'}";
    // 用tio-websocket，服务器发送到客户端的Packet都是WsResponse
    WsResponse wsResponse = WsResponse.fromText(msg, CHARSET);
    // 群发
    Tio.sendToGroup(channelContext.tioConfig, GROUP_ID, wsResponse);
  }

  /**
   * 字节消息（binaryType = arraybuffer）过来后会走这个方法
   */
  @Override
  public Object onBytes(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
    return null;
  }

  /**
   * 当客户端发close flag时，会走这个方法
   */
  @Override
  public Object onClose(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
    Tio.remove(channelContext, "receive close flag");
    return null;
  }

  /*
   * 字符消息（binaryType = blob）过来后会走这个方法
   */
  @Override
  public Object onText(WsRequest wsRequest, String text, ChannelContext channelContext) throws Exception {
    WsSessionContext wsSessionContext = (WsSessionContext) channelContext.get();
    String path = wsSessionContext.getHandshakeRequest().getRequestLine().path;
    log.info("path:{},收到ws消息:{}", path, text);

    if (Objects.equals("心跳内容", text)) {
      return null;
    }
    // channelContext.getToken()
    // String msg = channelContext.getClientNode().toString() + " 说：" + text;
    String msg = "{name:'" + channelContext.userid + "',message:'" + text + "'}";
    // 用tio-websocket，服务器发送到客户端的Packet都是WsResponse
    WsResponse wsResponse = WsResponse.fromText(msg, CHARSET);
    // 群发
    Tio.sendToGroup(channelContext.tioConfig, GROUP_ID, wsResponse);

    // 返回值是要发送给客户端的内容，一般都是返回null,返回为null,handler不会发送数据
    return null;
  }
}