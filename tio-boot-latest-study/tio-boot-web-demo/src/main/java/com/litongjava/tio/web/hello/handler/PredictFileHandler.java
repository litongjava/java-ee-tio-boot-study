package com.litongjava.tio.web.hello.handler;

import java.util.ArrayList;
import java.util.List;

import com.litongjava.chat.ChatImageFile;
import com.litongjava.chat.PlatformInput;
import com.litongjava.chat.UniChatClient;
import com.litongjava.chat.UniChatMessage;
import com.litongjava.chat.UniChatRequest;
import com.litongjava.chat.UniChatResponse;
import com.litongjava.consts.ModelPlatformName;
import com.litongjava.openrouter.OpenRouterModels;
import com.litongjava.tio.boot.http.TioRequestContext;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.common.UploadFile;
import com.litongjava.tio.http.server.handler.HttpRequestHandler;
import com.litongjava.tio.http.server.util.CORSUtils;
import com.litongjava.tio.utils.base64.Base64Utils;
import com.litongjava.tio.utils.http.ContentTypeUtils;
import com.litongjava.tio.utils.hutool.FilenameUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PredictFileHandler implements HttpRequestHandler {

  @Override
  public HttpResponse handle(HttpRequest httpRequest) throws Exception {
    HttpResponse response = TioRequestContext.getResponse();
    CORSUtils.enableCORS(response);
    UploadFile uploadFile = httpRequest.getUploadFile("file");
    byte[] data = uploadFile.getData();
    String name = uploadFile.getName();
    String systemPrompt = "识别图片中的文字";
    String result = predict(systemPrompt, data, name);
    return response.setBody(result);
  }

  private String predict(String systemPrompt, byte[] data, String filename) {
    String suffix = FilenameUtils.getSuffix(filename);

    String mimeType = ContentTypeUtils.getContentType(suffix);

    List<ChatImageFile> files = new ArrayList<>();
    String encodeImage = Base64Utils.encodeImage(data, mimeType);

    ChatImageFile file = ChatImageFile.base64(mimeType, encodeImage);
    files.add(file);

    UniChatMessage uniChatMessage = new UniChatMessage();
    uniChatMessage.setContent("image");
    uniChatMessage.setFiles(files);

    PlatformInput platformInput = new PlatformInput(ModelPlatformName.OPENROUTER,
        OpenRouterModels.QWEN_QWEN2_5_VL_7B_INSTRUCT_FREE);
    UniChatRequest uniChatRequest = new UniChatRequest(platformInput);
    uniChatRequest.setSystemPrompt(systemPrompt);

    List<UniChatMessage> messages = new ArrayList<>();
    messages.add(uniChatMessage);
    uniChatRequest.setMessages(messages);

    try {
      UniChatResponse response = UniChatClient.generate(uniChatRequest);
      String content = response.getMessage().getContent();
      return content;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    return null;
  }

}
