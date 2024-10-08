package demo.mybatis.controller;

import java.util.Collection;

import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;

import com.jfinal.kit.Kv;
import com.litongjava.annotation.RequestPath;

import demo.mybatis.config.MySqlSessionManager;
import demo.mybatis.mapper.SystemAdminMapper;
import demo.mybatis.model.SystemAdmin;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestPath("/mybatis2")
public class MybatisController2 {

  public Kv getMappers() {
    SqlSession sqlSession = MySqlSessionManager.getSqlSession();
    Configuration configuration = sqlSession.getConfiguration();
    MapperRegistry mapperRegistry = configuration.getMapperRegistry();
    Collection<Class<?>> mappers = mapperRegistry.getMappers();
    for (Class<?> c : mappers) {
      log.info("c:{}", c);
    }

    SystemAdminMapper mapper = sqlSession.getMapper(SystemAdminMapper.class);
    log.info("mapper:{}", mapper);
    SystemAdmin systemAdmin = mapper.getSystemAdmin(null);
    Kv kv = Kv.by("data", systemAdmin);

    return kv;
  }
}