package com.litongjava.tio.chatgpt.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.tio.core.ChannelContext;
import com.litongjava.tio.core.Tio;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.server.sse.SseBytesPacket;
import com.litongjava.tio.utils.environment.EnvironmentUtils;
import com.litongjava.tio.utils.json.Json;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OpenaiV1ChatHandler {

  public HttpResponse completions(HttpRequest httpRequest) throws IOException {
    ChannelContext channelContext = httpRequest.getChannelContext();

    // 设置sse请求头
    HttpResponse httpResponse = new HttpResponse(httpRequest).setServerSentEventsHeader();

    // 发送http响应包,告诉客户端保持连接
    Tio.send(channelContext, httpResponse);

    // 处理数据
    processData(channelContext);

    // 告诉处理器不要将消息发送给客户端
    return new HttpResponse().setSend(false);
  }

  private void processData(ChannelContext channelContext) throws IOException {

    String apiKey = EnvironmentUtils.get("OPENAI_API_KEY");

    OkHttpClient client = Aop.get(OkHttpClient.class);

    List<ChatgptMessage> messages = new ArrayList<>();
    messages.add(new ChatgptMessage("system", "你是一名经验丰富的软件开发工程师"));
    messages.add(new ChatgptMessage("user", "简述浏览器发送请求到收到响应的流程"));

    CompletionsModel completionsModel = new CompletionsModel();
//    completionsModel.setModel("gpt-3.5-turbo");
    completionsModel.setModel("gpt-4-turbo");
    completionsModel.setMessages(messages);
    completionsModel.setStream(true);

    String url = "https://api.openai.com/v1/chat/completions";
    MediaType mediaType = MediaType.parse("application/json");
    String content = Json.getJson().toJson(completionsModel);

    RequestBody body = RequestBody.create(mediaType, content);

    Request request = new Request.Builder() //
        .url(url) //
        .method("POST", body) //
        .addHeader("Content-Type", "application/json") //
        .addHeader("Authorization", "Bearer " + apiKey) //
        .build();
    Response response = client.newCall(request).execute();

    ResponseBody responseBody = response.body();
    if (responseBody != null) {
      // 转成BufferedReader是将字节转为字符
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(responseBody.byteStream()))) {
        String line;
        while ((line = reader.readLine()) != null) {
          System.out.println(line);
          // byte[] bytes = (line + "\n\n").getBytes();
          // 必须添加一个回车符号
          byte[] bytes = (line + "\n").getBytes();
          SseBytesPacket ssePacket = new SseBytesPacket(bytes);
          // 再次向客户端发送消息
          Tio.send(channelContext, ssePacket);
        }
      }
      //
    }
    Tio.remove(channelContext, "remove sse");
  }
}
