package com.issues02;

import com.litongjava.jfinal.aop.annotation.Service;

/**
 * Created by litonglinux@qq.com on 12/16/2023_4:08 PM
 */
@Service
public class DemoServiceImpl implements DemoService {

  public String Hello(){
    return "Hello";
  }
}
