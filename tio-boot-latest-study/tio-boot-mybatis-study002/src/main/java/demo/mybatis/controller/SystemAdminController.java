package demo.mybatis.controller;

import com.litongjava.annotation.RequestPath;

import demo.mybatis.config.MySqlSessionManager;
import demo.mybatis.mapper.SystemAdminMapper;
import demo.mybatis.model.SystemAdmin;

@RequestPath("/SystemAdmin")
public class SystemAdminController {

  public SystemAdmin getSystemAdmin() {
    SystemAdminMapper mapper = MySqlSessionManager.getMapper(SystemAdminMapper.class);
    SystemAdmin systemAdmin = mapper.getSystemAdmin(null);
    return systemAdmin;
  }
}