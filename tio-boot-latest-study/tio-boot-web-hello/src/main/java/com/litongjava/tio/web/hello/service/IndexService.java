package com.litongjava.tio.web.hello.service;

import java.util.HashMap;
import java.util.Map;

public class IndexService {

  public Map<String, String> index() {
    Map<String, String> ret = new HashMap<>();
    ret.put("data", "Hello 11");
    return ret;
  }
}
