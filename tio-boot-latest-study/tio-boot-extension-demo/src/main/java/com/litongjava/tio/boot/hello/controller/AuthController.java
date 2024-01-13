package com.litongjava.tio.boot.hello.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.litongjava.tio.http.common.HeaderName;
import com.litongjava.tio.http.common.HeaderValue;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.http.server.util.Resps;
import com.litongjava.tio.utils.resp.RespVo;

import cn.hutool.jwt.JWTUtil;

/**
 * token永不过期
 */
@RequestPath("/auth")
public class AuthController {

  // 使用HmacSHA256签名算法的密钥
  byte[] key = "litongjava".getBytes();
  // 用于存储tokens的Map
  private static Map<String, String> tokenStore = new HashMap<>();

  @RequestPath("/login")
  public HttpResponse login(HttpRequest request, String username, String password) {
    if (isValidUser(username, password)) {
      // 生成JWT token
      String accessToken = createJWTToken(username, key);

      // 将token存储到Map中
      tokenStore.put(username, accessToken);

      // 创建HttpResponse，并添加token作为头部
      HeaderName headerName = HeaderName.from("Authorization");
      HeaderValue headerValue = HeaderValue.from("Bearer " + accessToken);

      HttpResponse httpResponse = Resps.json(request, RespVo.ok());
      httpResponse.addHeader(headerName, headerValue);
      return httpResponse;
    } else {
      return Resps.json(request, RespVo.fail("Invalid username or password"));
    }
  }

  @RequestPath("/verifyToken")
  public RespVo verifyToken(String token) {
    // 验证token是否有效
    if (isValidToken(token)) {
      return RespVo.ok();
    } else {
      return RespVo.fail("Token is invalid or expired");
    }
  }

  private boolean isValidUser(String username, String password) {
    // 在这里实现您的用户验证逻辑
    // 暂时返回true模拟验证成功
    return true;
  }

  public static String createJWTToken(String username, byte[] key) {
    // 载荷，可根据需要添加更多数据
    Map<String, Object> payload = new HashMap<>();
    payload.put("username", username);

    // 设置过期时间，例如1小时后
    long expirationMillis = System.currentTimeMillis() + 3600000; // 1小时 = 3600000毫秒
    Date expiration = new Date(expirationMillis);
    payload.put("exp", expiration.getTime() / 1000); // JWT通常使用秒为单位的时间戳

    // 生成JWT token
    return JWTUtil.createToken(payload, key);
  }

  private boolean isValidToken(String token) {
    // 使用相同的密钥验证token
    return JWTUtil.verify(token, key);
  }
}
