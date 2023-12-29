package demo.mybatis.plus.services;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionManager;

import com.litongjava.jfinal.aop.Autowired;

import demo.mybatis.plus.mapper.UserMapper;
import demo.mybatis.plus.model.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServcie {
  @Autowired
  private SqlSessionManager sqlSessionManager;
  public List<User> selectList() {
    try (SqlSession sqlSession = sqlSessionManager.openSession()) {
      log.info("sqlSession:{}", sqlSession);
      UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
      log.info("userMapper:{}", userMapper);
      return userMapper.selectList(null);
    }
  }
}