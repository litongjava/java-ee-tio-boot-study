package com.litongjava.tio.boot.hello.job;

import com.xxl.job.core.handler.IJobHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyJobHandler extends IJobHandler {
  @Override
  public void execute() throws Exception {
    log.info("执行代码");
  }
}