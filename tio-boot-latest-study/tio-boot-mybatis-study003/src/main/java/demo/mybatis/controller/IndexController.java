package demo.mybatis.controller;

import com.litongjava.annotation.RequestPath;

@RequestPath("/")
public class IndexController {

  @RequestPath()
  public String index() {
    return "index";
  }

}