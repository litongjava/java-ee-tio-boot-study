package demo.mybatis.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtils {

  public static final String resource = "mybatis.xml";
  private static SqlSessionFactory factory;
  static {
    InputStream inputStream = null;
    try {
      inputStream = Resources.getResourceAsStream(resource);
      factory = new SqlSessionFactoryBuilder().build(inputStream);
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }

  /**
   * 返回factory
   * @return
   */
  public static SqlSessionFactory getFactory() {
    return factory;
  }

  /**
   * 打开会话
   * @return
   */
  public static SqlSession openSession(boolean autoCommit) {
    return factory.openSession(autoCommit);
  }

  public static SqlSession openSession() {
    return factory.openSession(true);
  }

}