package com.litongjava.tio.web.hello.handler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import com.litongjava.tio.boot.exception.TioBootExceptionHandler;
import com.litongjava.tio.http.common.HttpRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GlobalExceptionHadler implements TioBootExceptionHandler {

  @Override
  public void handler(HttpRequest request, Throwable e) {
    String requestURL = request.getRequestURI();
    Map<String, String> headers = request.getHeaders();
    String bodyString = request.getBodyString();
    
    // 获取完整的堆栈跟踪
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    e.printStackTrace(pw);
    String stackTrace = sw.toString();
    
    log.info("{},{},{},{}", requestURL.toString(), headers, bodyString, stackTrace);

  }

}
