package com.litongjava.tio.web.hello.task;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyTask implements Runnable {

  @Override
  public void run() {
    log.info("执行任务");
  }

}
