package com.litongjava.tio.boot.djl;

import com.litongjava.hotswap.debug.Diagnostic;
import com.litongjava.hotswap.kit.HotSwapUtils;
import com.litongjava.hotswap.server.RestartServer;
import com.litongjava.hotswap.wrapper.forkapp.ForkAppBootArgument;
import com.litongjava.tio.boot.TioApplication;
import com.litongjava.tio.boot.context.Context;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SelfRestart implements RestartServer {
  public boolean isStarted() {
    return ForkAppBootArgument.getContext().isRunning();
  }

  public void restart() {
    System.err.println("loading");
    long start = System.currentTimeMillis();

    stop();
    // 获取一个新的ClassLoader
    ClassLoader hotSwapClassLoader = HotSwapUtils.newClassLoader();
    if (Diagnostic.isDebug()) {
      log.info("new classLoader:{}", hotSwapClassLoader);
    }

    // 在启动新的spring-boot应用之前必须设置上下文加载器
    Thread.currentThread().setContextClassLoader(hotSwapClassLoader);

    // 获取启动类和启动参数
    Class<?> clazz = ForkAppBootArgument.getBootClazz();
    String[] args = ForkAppBootArgument.getArgs();
    // 启动Application
    start(clazz, args);
    long end = System.currentTimeMillis();
    System.err.println("Loading complete in " + (end - start) + " ms (^_^)\n");
  }

  @Override
  public void start(Class<?> primarySource, String[] args) {
    Context context = TioApplication.run(primarySource, args);
    ForkAppBootArgument.setContext(context);
  }

  @Override
  public void stop() {
    ForkAppBootArgument.getContext().close();
  }
}