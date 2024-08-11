package com.litongjava.tio.dubbo.demo001.service.impl;

import com.litongjava.tio.dubbo.demo001.service.HiService;

public class HiServiceImpl implements HiService {

  @Override
  public String sayHi(String name) {
    return "Hi " + name;
  }

}
