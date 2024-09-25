package com.litongjava.jave.server.controller;

import com.litongjava.tio.http.server.annotation.RequestPath;

import lombok.extern.slf4j.Slf4j;

@RequestPath("/")
@Slf4j
public class IndexController {
  @RequestPath()
  public String index() {
    log.debug("debug:{}","text debug");
    return "index";
  }
}
