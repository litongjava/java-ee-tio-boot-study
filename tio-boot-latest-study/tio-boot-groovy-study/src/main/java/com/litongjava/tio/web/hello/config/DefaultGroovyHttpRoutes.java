package com.litongjava.tio.web.hello.config;

import java.util.Set;

import com.litongjava.grovvy.GroovyScriptManager;
import com.litongjava.jfinal.plugin.redis.Redis;
import com.litongjava.tio.http.server.handler.HttpRequestRouteHandler;
import com.litongjava.tio.http.server.router.GroovyHttpRoutes;

import redis.clients.jedis.Jedis;

public class DefaultGroovyHttpRoutes implements GroovyHttpRoutes {

  String redisKeyName = "HttpRequestRouteHandlers";

  public DefaultGroovyHttpRoutes(String redisKeyName) {
    this.redisKeyName = redisKeyName;
  }

  public void add(String path, HttpRequestRouteHandler handler) {
    throw new RuntimeException("not supported");
  }

  /**
   * find route
   * /* 表示匹配任何以特定路径开始的路径，/** 表示匹配该路径及其下的任何子路径
   */
  public HttpRequestRouteHandler find(String path) {
    try (Jedis jedis = Redis.use().getJedis()) {
      String scriptValue = jedis.hget(redisKeyName, path);

      if (scriptValue != null) {
        return GroovyScriptManager.getHttpRequestHandler(scriptValue);
      }

      Set<String> paths = jedis.hkeys(redisKeyName);

      // Check for wildcard matches
      for (String routePath : paths) {
        if (routePath.endsWith("/*")) {
          String baseRoute = routePath.substring(0, routePath.length() - 1);
          if (path.startsWith(baseRoute)) {
            scriptValue = jedis.hget(redisKeyName, routePath);
            if (scriptValue != null) {
              return GroovyScriptManager.getHttpRequestHandler(scriptValue);
            }

          }
        } else if (routePath.endsWith("/**")) {
          String baseRoute = routePath.substring(0, routePath.length() - 2);
          if (path.startsWith(baseRoute)) {
            scriptValue = jedis.hget(redisKeyName, routePath);
            if (scriptValue != null) {
              return GroovyScriptManager.getHttpRequestHandler(scriptValue);
            }
          }
        }
      }
    }
    return null;

  }
}
