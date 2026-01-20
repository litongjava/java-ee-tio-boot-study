package com.litongjava.tio.web.hello.controller;

import com.litongjava.tio.boot.http.TioRequestContext;
import com.litongjava.tio.http.common.HttpResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DocumentController {

  public HttpResponse export(Long documentId) {

    String downloadFilename = "my.md";

    log.info("filename:{}", downloadFilename);

    String contentType = "text/markdown; charset=utf-8";
    HttpResponse httpResponse = TioRequestContext.getResponse();
    httpResponse.setContentType(contentType);
    httpResponse.setHeader("Content-Disposition", "attachment; filename=\"" + downloadFilename + "\"");
    httpResponse.setBody("Hi");

    return httpResponse;
  }
}
