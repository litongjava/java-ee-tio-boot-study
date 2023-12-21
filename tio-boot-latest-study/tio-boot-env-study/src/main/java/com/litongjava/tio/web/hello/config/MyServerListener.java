package com.litongjava.tio.web.hello.config;

import com.litongjava.hotswap.watcher.HotSwapWatcher;
import com.litongjava.hotswap.wrapper.tio.boot.TioBootArgument;
import com.litongjava.hotswap.wrapper.tio.boot.TioBootRestartServer;
import com.litongjava.jfinal.aop.AopManager;
import com.litongjava.tio.boot.constatns.ConfigKeys;
import com.litongjava.tio.boot.context.Context;
import com.litongjava.tio.boot.server.TioBootServerListener;
import com.litongjava.tio.utils.enviorment.EnviormentUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyServerListener implements TioBootServerListener {

  protected static volatile HotSwapWatcher hotSwapWatcher;

  @Override
  public void boforeStart(Class<?>[] primarySources, String[] args) {
  }

  @Override
  public void afterStarted(Class<?>[] primarySources, String[] args, Context context) {
    String env = EnviormentUtils.get(ConfigKeys.appEnv);
    if("dev".endsWith(env)) {
      TioBootArgument tioBootArgument = new TioBootArgument(primarySources, args, context, true);
      AopManager.me().addSingletonObject(tioBootArgument);

      if (hotSwapWatcher == null) {
        // 使用反射执行下面的代码
         log.info("start hotSwapWatcher");
        hotSwapWatcher = new HotSwapWatcher(new TioBootRestartServer());
        hotSwapWatcher.start();
      }
    }
  }
}
