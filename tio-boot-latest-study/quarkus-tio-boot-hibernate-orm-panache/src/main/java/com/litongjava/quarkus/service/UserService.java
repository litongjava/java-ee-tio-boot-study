package com.litongjava.quarkus.service;

import java.util.Map;

import com.litongjava.quarkus.entity.User;
import com.litongjava.quarkus.repo.UserRepo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {

  @Inject
  UserRepo userRepo;

  @Transactional
  public Map<String, Object> createUser(String username, String password) {
    User exists = userRepo.findByUsername(username);
    if (exists != null) {
      return Map.of("ok", false, "msg", "username already exists");
    }

    User u = new User();
    u.username = username;
    u.password = password;
    u.createdAt = System.currentTimeMillis();
    userRepo.persist(u);

    return Map.of("ok", true, "id", u.id, "username", u.username);
  }

  @Transactional
  public Map<String, Object> getUser(Long id) {
    User u = userRepo.findById(id);
    if (u == null) {
      return Map.of("ok", false, "msg", "not found");
    }
    return Map.of("ok", true, "id", u.id, "username", u.username, "createdAt", u.createdAt);
  }
}