package com.litongjava.quarkus.config;

import io.quarkus.arc.Arc;

public final class QuarkusBeans {
  private QuarkusBeans() {
  }

  public static <T> T get(Class<T> type) {
    return Arc.container().instance(type).get();
  }
}
