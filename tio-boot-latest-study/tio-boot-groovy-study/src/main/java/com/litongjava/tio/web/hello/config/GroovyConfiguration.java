package com.litongjava.tio.web.hello.config;

import com.litongjava.jfinal.aop.annotation.ABean;
import com.litongjava.jfinal.aop.annotation.BeforeStartConfiguration;
import groovy.lang.GroovyShell;

@BeforeStartConfiguration
public class GroovyConfiguration {

  @ABean
  public GroovyShell groovyShell() {
    return new GroovyShell();
  }

}
