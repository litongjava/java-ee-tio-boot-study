package demo.mybatis.config;

import org.apache.ibatis.session.SqlSessionManager;

public class MySqlSessionManager {

  private static SqlSessionManager sqlSessionManager;

  public static void setSqlSessionManager(SqlSessionManager manager) {
    sqlSessionManager = manager;
  }

  public static SqlSessionManager getSqlSessionManager() {
    return sqlSessionManager;
  }
  

  public static <T> T getMapper(Class<T> clazz) {
    return sqlSessionManager.getMapper(clazz);
  }
}