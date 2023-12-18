package com.litongjava.tio.web.hello;

import com.litongjava.ai.server.padddle.ocr.config.PaddleOcrConfig;
import com.litongjava.ai.server.padddle.ocr.controller.PaddleOcrController;
import com.litongjava.jfinal.aop.annotation.ComponentScan;
import com.litongjava.jfinal.aop.annotation.Controller;
import com.litongjava.jfinal.aop.annotation.Import;
import com.litongjava.tio.boot.TioApplication;
import com.litongjava.tio.http.server.annotation.RequestPath;

@ComponentScan
@Controller
@RequestPath("/")
@Import({ PaddleOcrConfig.class, PaddleOcrController.class })
public class HelloApp {
  public static void main(String[] args) {
    TioApplication.run(HelloApp.class, args);
  }

  @RequestPath()
  public String index() {
    return "index";
  }
}