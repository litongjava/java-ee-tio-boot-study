package com.litongjava.tio.web.hello.controller;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.CompletionHandler;
import java.util.HashMap;
import java.util.Map;

import com.litongjava.tio.boot.http.TioControllerContext;
import com.litongjava.tio.core.ChannelContext;
import com.litongjava.tio.core.TioConfig;
import com.litongjava.tio.core.intf.AioHandler;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.http.server.util.Resps;

import lombok.extern.slf4j.Slf4j;

@RequestPath("/async")
@Slf4j
public class AsyncController {

  public Map<String, Object> index(HttpRequest request) {
    ChannelContext channelContext = request.getChannelContext();
    TioConfig tioConfig = channelContext.getTioConfig();
    AioHandler aioHandler = tioConfig.getAioHandler();
    String token = channelContext.getToken();

    Map<String, Object> data = new HashMap<>();
    data.put("channelContext", channelContext.toString());
    data.put("tioConfig", tioConfig.toString());
    data.put("userId", channelContext.userid);
    data.put("token", token);
    data.put("asynchronousSocketChannel", channelContext.asynchronousSocketChannel.toString());
    data.put("aioHandler", aioHandler.toString());
    log.info("data:{}", data);
    return data;
  }

  public String writeHttpResponse(HttpRequest request) {
    ChannelContext channelContext = request.getChannelContext();
    // 准备发送的数据
    Map<String, Object> data = new HashMap<>();
    data.put("code", 1);

    // 封装为httpResponse
    HttpResponse httpResponse = TioControllerContext.getResponse();
    httpResponse = Resps.json(httpResponse, data);

    // 调用Handler进行解码
    TioConfig tioConfig = channelContext.getTioConfig();
    AioHandler aioHandler = tioConfig.getAioHandler();
    ByteBuffer writeBuffer = aioHandler.encode(httpResponse, tioConfig, channelContext);

    // 异步写入数据到客户端
    channelContext.asynchronousSocketChannel.write(writeBuffer);

    return null;
  }

  public String writeHttpResponseWithCompletionHandler(HttpRequest request) {
    ChannelContext channelContext = request.getChannelContext();
    // 准备发送的数据
    Map<String, Object> data = new HashMap<>();
    data.put("code", 1);

    // 封装为httpResponse
    HttpResponse httpResponse = TioControllerContext.getResponse();
    httpResponse = Resps.json(httpResponse, data);

    // 调用Handler进行解码
    TioConfig tioConfig = channelContext.getTioConfig();
    AioHandler aioHandler = tioConfig.getAioHandler();
    ByteBuffer writeBuffer = aioHandler.encode(httpResponse, tioConfig, channelContext);

    // 异步写入数据到客户端
    channelContext.asynchronousSocketChannel.write(writeBuffer, request, new CompletionHandler<Integer, HttpRequest>() {

      @Override
      public void completed(Integer result, HttpRequest attachment) {
        // 进行其他操作
        log.info("result:{},attachment:{}", result, attachment);

        // 手动关闭连接
        try {
          channelContext.asynchronousSocketChannel.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      @Override
      public void failed(Throwable exc, HttpRequest attachment) {
        try {
          channelContext.asynchronousSocketChannel.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

    });

    return null;
  }
}
