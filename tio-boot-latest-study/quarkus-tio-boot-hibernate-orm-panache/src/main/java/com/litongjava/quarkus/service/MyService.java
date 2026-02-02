package com.litongjava.quarkus.service;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class MyService {

  public Map<String, Object> work() {
    Map<String, Object> data = new HashMap<>();
    data.put("from", "quarkus");
    data.put("status", "ok");
    data.put("time", System.currentTimeMillis());
    return data;
  }
}
