package com.litongjava.tio.web.hello.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.litongjava.jfinal.aop.annotation.AInitialization;
import com.litongjava.jfinal.aop.annotation.BeforeStartConfiguration;
import com.litongjava.jfinal.plugin.redis.Cache;
import com.litongjava.jfinal.plugin.redis.Redis;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.utils.hutool.ResourceUtil;

@BeforeStartConfiguration
public class HttpServerRequestHanlderConfig {

  @AInitialization
  public void httpRoutes() {

    Cache bbsCache = Redis.use();
    String redisKeyName = "HttpRequestRouteHandlers";
    String groovyScriptFolder = "groovy";

    Map<String, String> hash = new HashMap<>();

    setPathScript(hash, "/web_hello", groovyScriptFolder + "/web_hello.groovy");
    setPathScript(hash, "/user", groovyScriptFolder + "/users.groovy");

    bbsCache.hmsetRawString(redisKeyName, hash);

    // 创建simpleHttpRoutes
    DefaultGroovyHttpRoutes dbHttpRoutes = new DefaultGroovyHttpRoutes(redisKeyName);
    // 将simpleHttpRoutes添加到TioBootServer
    TioBootServer.me().setDbHttpRoutes(dbHttpRoutes);
  }

  private void setPathScript(Map<String, String> hash, String path, String filename) {
    try (InputStream inputStream = ResourceUtil.getResourceAsStream(filename)) {
      if (inputStream != null) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
          String script = reader.lines().collect(Collectors.joining("\n"));

          hash.put(path, script);
        }
      } else {
        throw new RuntimeException("Script file not found inputStream is null: " + filename);
      }
    } catch (IOException e) {
      throw new RuntimeException("Script file not found: " + filename);
    }
  }
}