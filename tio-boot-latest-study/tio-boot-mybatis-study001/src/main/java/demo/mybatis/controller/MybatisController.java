package demo.mybatis.controller;

import java.sql.Connection;

import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jfinal.kit.Kv;
import com.litongjava.annotation.RequestPath;

import demo.mybatis.mapper.SystemAdminMapper;
import demo.mybatis.model.SystemAdmin;
import demo.mybatis.utils.MybatisUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestPath("/mybatis")
public class MybatisController {
  public Kv getMapper() {
    SqlSessionFactory factory = MybatisUtils.getFactory();
    log.info("factory:{}", factory);
    SqlSession sqlSession = MybatisUtils.openSession(true);
    log.info("sqlSession:{}", sqlSession);
    Connection connection = sqlSession.getConnection();
    log.info("connection:{}", connection);
    Configuration configuration = sqlSession.getConfiguration();
    log.info("configuration:{}", configuration);
    MapperRegistry mapperRegistry = configuration.getMapperRegistry();
    log.info("mapperRegistry:{}", mapperRegistry);
    SystemAdminMapper mapper = sqlSession.getMapper(SystemAdminMapper.class);
    log.info("mapper:{}", mapper);
    SystemAdmin systemAdmin = mapper.getSystemAdmin(null);
    Kv kv = Kv.by("data", systemAdmin);
    
    kv.set("factory", factory.toString()).set("sqlSession:", sqlSession.toString()).set("connection", connection.toString());
    kv.set("configuration", configuration.toString()).set("mapperRegistry", mapperRegistry.toString());
    kv.set("mapper", mapper.toString());
    return kv;
  }
}