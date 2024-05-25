package com.litongjava.tio.web.hello.config;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.http.server.router.HttpReqeustSimpleHandlerRoute;
import com.litongjava.tio.web.hello.handler.AudioChunkHandler;

public class HttpRequestHanlderConfig {

  public void config() {

    // 获取router
    HttpReqeustSimpleHandlerRoute r = TioBootServer.me().getHttpReqeustSimpleHandlerRoute();

    // 创建handler
    AudioChunkHandler AudioChunkHandler = new AudioChunkHandler();

    // 添加action
    r.add("/tts", AudioChunkHandler::tts);

  }
}

