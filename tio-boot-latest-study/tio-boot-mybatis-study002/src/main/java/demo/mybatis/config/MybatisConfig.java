package demo.mybatis.config;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.litongjava.annotation.ABean;
import com.litongjava.annotation.AConfiguration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@AConfiguration
public class MybatisConfig {

  // 配置文件名称
  private String mybatisConfigXml = "mybatis.xml";

  /**
   * 获取SqlSession
   *
   * @return
   */
  @ABean(destroyMethod = "close")
  public SqlSession getSqlSession() {
    Reader reader = null;
    try {
      reader = Resources.getResourceAsReader(mybatisConfigXml);
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
      // 通过sqlSessionFactory打开一个数据库会话
      SqlSession sqlSession = sqlSessionFactory.openSession();
      MySqlSessionManager.setSqlSession(sqlSession);
      return sqlSession;
    } catch (IOException e) {
      log.error("can not get sqlSession", e);
    }
    return null;
  }

}