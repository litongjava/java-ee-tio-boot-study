package com.litongjava.spring.boot.tio.boot.demo01.controller;

import com.litongjava.jfinal.spring.SpringBeanContextUtils;
import com.litongjava.tio.http.server.annotation.RequestPath;

@RequestPath("/spring")
public class SpringController {

  public String[] beans() {
    return SpringBeanContextUtils.getContext().getBeanDefinitionNames();
  }
}