package com.issues02;

import com.litongjava.jfinal.aop.Autowired;
import com.litongjava.jfinal.aop.Inject;
import com.litongjava.jfinal.aop.annotation.Controller;

import java.lang.annotation.Inherited;

/**
 * Created by litonglinux@qq.com on 12/16/2023_4:07 PM
 */
@Controller
public class DemoController {

//  @Inject
//  private DemoService demoService;

  @Autowired
  private DemoService demoService;

  public String hello() {
    return demoService.Hello();
  }
}
