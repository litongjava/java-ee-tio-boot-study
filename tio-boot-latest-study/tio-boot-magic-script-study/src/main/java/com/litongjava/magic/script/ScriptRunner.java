package com.litongjava.magic.script;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.ssssssss.script.MagicScriptContext;

public class ScriptRunner {

  public static void main(String[] args) {
    // 设置文件路径
    Path path = Paths.get("src/main/resources/ms/helloworld.ms");

    try {
      // 读取脚本文件
      byte[] bytes = Files.readAllBytes(path);
      String script = new String(bytes, StandardCharsets.UTF_8);

      // 创建脚本上下文
      MagicScriptContext context = new MagicScriptContext();

      // 执行脚本
      Object result = ScriptManager.executeScript(script, context);
      System.out.println(result);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
