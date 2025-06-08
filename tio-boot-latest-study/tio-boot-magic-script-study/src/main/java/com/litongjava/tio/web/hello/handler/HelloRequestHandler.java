package com.litongjava.tio.web.hello.handler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.ssssssss.script.MagicScriptContext;

import com.litongjava.magic.script.ScriptManager;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;

public class HelloRequestHandler {

  public HttpResponse hi(HttpRequest httpRequest) throws IOException {
    // 设置文件路径
    Path path = Paths.get("src/main/resources/ms/web_hello.ms");

    // 读取脚本文件
    byte[] bytes = Files.readAllBytes(path);
    String script = new String(bytes, StandardCharsets.UTF_8);

    // 创建脚本上下文
    MagicScriptContext context = new MagicScriptContext();
    //添加request
    context.set("request", httpRequest);

    
    // 执行脚本
    return (HttpResponse) ScriptManager.executeScript(script, context);
    
  }
}
