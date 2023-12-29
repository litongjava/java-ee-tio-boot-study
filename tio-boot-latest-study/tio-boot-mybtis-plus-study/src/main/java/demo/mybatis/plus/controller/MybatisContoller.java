package demo.mybatis.plus.controller;

import java.util.Collection;

import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionManager;

import com.litongjava.jfinal.aop.Autowired;
import com.litongjava.tio.http.server.annotation.RequestPath;

import demo.mybatis.plus.mapper.UserMapper;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

/**
 * @author create by Ping E Lee on 2022年3月29日 下午3:04:52 
 *
 */
@RequestPath("/mybatis")
@Slf4j
public class MybatisContoller {

  @Autowired
  private SqlSessionManager sqlSessionManager;

  public String mappers() {
    @Cleanup
    SqlSession sqlSession = sqlSessionManager.openSession();
    MapperRegistry mapperRegistry = sqlSession.getConfiguration().getMapperRegistry();
    Collection<Class<?>> mappers = mapperRegistry.getMappers();
    for (Class<?> c : mappers) {
      log.info("c:{}", c);
    }
    return mappers.toString();

  }

  public String getUserMapper() {
    @Cleanup
    SqlSession sqlSession = sqlSessionManager.openSession();
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    log.info("mapper:{}", mapper);
    return mapper.toString();
  }
}