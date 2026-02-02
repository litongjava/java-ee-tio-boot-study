package com.litongjava.quarkus.repo;

import com.litongjava.quarkus.entity.User;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepo implements PanacheRepository<User> {

  public User findByUsername(String username) {
    return find("username", username).firstResult();
  }
}