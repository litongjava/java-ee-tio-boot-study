package com.litongjava.quarkus.config;

import com.litongjava.context.Context;
import com.litongjava.tio.boot.TioApplication;

import io.quarkus.logging.Log;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

@ApplicationScoped
public class TioBootLifecycle {
  private Context context;

  void onStart(@Observes StartupEvent ev) {
    long start = System.currentTimeMillis();
    context = TioApplication.run(new WebHelloConfig());
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "ms");
  }

  void onStop(@Observes ShutdownEvent ev) {
    if (context != null) {
      Log.info("close");
      context.close();
    }
  }
}