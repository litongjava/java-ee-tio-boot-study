package com.litongjava.tio.web.hello.config;

import java.util.concurrent.ExecutorService;

import com.litongjava.jfinal.aop.annotation.AConfiguration;
import com.litongjava.jfinal.aop.annotation.AInitialization;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.utils.thread.ThreadUtils;

@AConfiguration
public class ExecutorServiceConfig {

  @AInitialization
  public void config() {
    // 创建包含10个线程的线程池
    ExecutorService executor = ThreadUtils.newFixedThreadPool(10);

    // 项目关闭时，关闭线程池
    TioBootServer.me().addDestroyMethod(() -> {
      if (executor != null && !executor.isShutdown()) {
        executor.shutdown();
      }
    });
  }
}
