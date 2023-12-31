package com.litongjava.tio.boot.hello.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.litongjava.jfinal.aop.annotation.Controller;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.http.server.util.Resps;
import com.litongjava.tio.utils.resp.RespVo;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;

@Controller
@RequestPath("/qr")
public class QrController {

  @RequestPath("")
  public HttpResponse qr(HttpRequest request, String content) {
    // 获取要生成的二维码内容
    if (StrUtil.isBlank(content)) {
      return Resps.json(request, RespVo.fail("No content provided for QR code"));
    }

    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
      // 生成二维码图片，并写入到输出流
      QrCodeUtil.generate(content, 300, 300, "png", outputStream);

      // 将输出流转换为字节数组
      byte[] qrCodeBytes = outputStream.toByteArray();

      // 使用 Resps 工具类创建一个包含二维码图片的响应
      HttpResponse response = Resps.bytesWithContentType(request, qrCodeBytes, "image/png");

      // 返回响应
      return response;
    } catch (IOException e) {
      e.printStackTrace();
      return Resps.json(request, RespVo.fail("Error generating QR code"));
    }
  }
}
