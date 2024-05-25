package com.litongjava.tio.web.hello.handler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.litongjava.tio.boot.http.TioControllerContext;
import com.litongjava.tio.core.ChannelContext;
import com.litongjava.tio.core.Tio;
import com.litongjava.tio.http.common.HeaderName;
import com.litongjava.tio.http.common.HeaderValue;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.common.encoder.ChunkEncoder;
import com.litongjava.tio.http.common.sse.SseBytesPacket;
import com.litongjava.tio.utils.http.ContentTypeUtils;
import com.litongjava.tio.utils.hutool.ResourceUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AudioChunkHandler {

  public HttpResponse tts(HttpRequest httpRequest) {
    ChannelContext channelContext = httpRequest.getChannelContext();

    HttpResponse response = TioControllerContext.getResponse();

    URL resource = ResourceUtil.getResource("samples/Blowin_in_the_Wind-16k.pcm");
    if (resource == null) {
      throw new RuntimeException("Resource not found");
    }

    String fileExt = "pcm"; // 文件扩展名，根据实际情况设置
    String contentType = ContentTypeUtils.getContentType(fileExt);

    // 告诉默认的处理器不发送该包
    response.setSend(false);
    // 设置为流式输出,这样不会计算content-length,because Content-Length can't be present with Transfer-Encoding
    response.setStream(true);
    // 设置响应头
    response.addHeader(HeaderName.Transfer_Encoding, HeaderValue.from("chunked"));
    response.addHeader(HeaderName.Content_Type, HeaderValue.from(contentType));

    // 打开文件无异常 发送初始响应头
    Tio.send(channelContext, response);

    try (InputStream inputStream = resource.openStream()) {

      byte[] buffer = new byte[1024 * 10];
      int bytesRead;
      int i = 0;
      while ((bytesRead = inputStream.read(buffer)) != -1) {
        i++;
        byte[] chunkData = new byte[bytesRead];
        System.arraycopy(buffer, 0, chunkData, 0, bytesRead);
        SseBytesPacket ssePacket = new SseBytesPacket(ChunkEncoder.encodeChunk(chunkData, bytesRead));
        Tio.send(channelContext, ssePacket);
        log.info("sned:{}:{}", i, bytesRead);
      }
      // 发送结束标志
      SseBytesPacket endPacket = new SseBytesPacket(ChunkEncoder.encodeChunk(new byte[0]));
      Tio.send(channelContext, endPacket);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return response;
  }
}
