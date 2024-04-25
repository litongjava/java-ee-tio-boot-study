package com.litongjava.tio.web.hello.config;

import java.net.URL;

import org.junit.Test;

import com.litongjava.tio.utils.hutool.ResourceUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpServerRequestHanlderConfigTest {

  @Test
  public void test() {
    String resourcePath = "groovy"; // 要读取的资源路径
    URL resource = ResourceUtil.getResource(resourcePath);
    log.info("getFile:{}",resource.getFile());
    log.info("getPath:{}",resource.getPath());
  }
}