package com.litongjava.tio.boot.hello.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.litongjava.jfinal.aop.annotation.Controller;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.http.server.util.Resps;
import com.litongjava.tio.utils.resp.RespVo;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;

@Controller
@RequestPath("/captcha")
public class CaptchaController {

  @RequestPath("")
  public HttpResponse captcha(HttpRequest request) {
    // 创建线性验证码
    LineCaptcha captcha = CaptchaUtil.createLineCaptcha(200, 100);

    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
      // 获取验证码图片，并写入到输出流
      captcha.write(outputStream);

      // 将输出流转换为字节数组
      byte[] captchaBytes = outputStream.toByteArray();

      // 使用 Resps 工具类创建一个包含验证码图片的响应
      HttpResponse response = Resps.bytesWithContentType(request, captchaBytes, "image/jpeg");

      // 返回响应
      return response;
    } catch (IOException e) {
      e.printStackTrace();
      return Resps.json(request, RespVo.fail("Error generating captcha"));
    }
  }
}
