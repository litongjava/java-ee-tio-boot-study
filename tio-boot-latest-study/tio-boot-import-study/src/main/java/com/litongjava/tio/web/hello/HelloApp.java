package com.litongjava.tio.web.hello;

import com.litongjava.ai.server.padddle.ocr.OcrServer;
import com.litongjava.jfinal.aop.annotation.ComponentScan;
import com.litongjava.jfinal.aop.annotation.Controller;
import com.litongjava.tio.boot.TioApplication;
import com.litongjava.tio.http.server.annotation.RequestPath;

//@ComponentScan(value = {}, excludeFilters = {
//    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = NoThingConfig.class),
//    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = PaddleOcrConfig.class) }
//)
@ComponentScan()
@Controller
@RequestPath("/")
//@Import({ PaddleOcrConfig.class, PaddleOcrController.class })

public class HelloApp {
  public static void main(String[] args) {
    TioApplication.run(new Class[] { HelloApp.class, OcrServer.class }, args);
  }

  @RequestPath()
  public String index() {
    return "index";
  }
}