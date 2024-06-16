package com.litongjava.tio.boot.demo.graphql.resolver;

import com.litongjava.tio.boot.demo.graphql.model.User;

public class UserResolver {
  public User getUser(String id) {
    // 在这里实现数据获取逻辑
    System.out.println("id:" + id);
    return new User(id, "John Doe", "john@example.com");
  }
}