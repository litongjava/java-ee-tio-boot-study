package com.litongjava.spring.boot.tio.boot.demo01.services;

import org.junit.Before;
import org.junit.Test;

import com.litongjava.jfinal.spring.SpringBeanContextUtils;
import com.litongjava.spring.boot.tio.boot.demo01.Applicaton;
import com.litongjava.tio.boot.TioApplication;
import com.litongjava.tio.utils.json.JsonUtils;

public class SpringContextText {

  @Before
  public void before() {
    TioApplication.run(Applicaton.class, new String[] {});
  }

  @Test
  public void test() {
    String[] beanDefinitionNames = SpringBeanContextUtils.getContext().getBeanDefinitionNames();
    System.out.println(JsonUtils.toJson(beanDefinitionNames));
  }
}
