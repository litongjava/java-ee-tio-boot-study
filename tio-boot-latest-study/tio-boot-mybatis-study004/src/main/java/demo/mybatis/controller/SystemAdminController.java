package demo.mybatis.controller;

import com.jfinal.kit.Kv;
import com.litongjava.annotation.Inject;
import com.litongjava.annotation.RequestPath;

import demo.mybatis.mapper.SystemAdminMapper;
import demo.mybatis.model.SystemAdmin;
import lombok.extern.slf4j.Slf4j;

@RequestPath("/SystemAdmin")
@Slf4j
public class SystemAdminController {

  @Inject
  private SystemAdminMapper systemAdminMapper;

  public Kv getSystemAdmin() {
    SystemAdmin systemAdmin = systemAdminMapper.getSystemAdmin(null);
    log.info("systemAdmin:{}", systemAdmin);
    return Kv.by("data1", systemAdmin);
  }
}