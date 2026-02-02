package com.litongjava.spring.boot.tio.boot.demo01.controller;

import com.litongjava.annotation.RequestPath;
import com.litongjava.jfinal.spring.SpringBeanContextUtils;

@RequestPath("/spring")
public class SpringController {

  public String[] beans() {
    return SpringBeanContextUtils.getContext().getBeanDefinitionNames();
  }
}
