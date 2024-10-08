package demo.mybatis.config;
import org.apache.ibatis.session.SqlSession;
public class MySqlSessionManager {

  private static SqlSession sqlSession;

  public static SqlSession getSqlSession() {
    return sqlSession;
  }

  public static void setSqlSession(SqlSession sqlSession) {
    MySqlSessionManager.sqlSession = sqlSession;
  }

  public static <T> T getMapper(Class<T> clazz) {
    return sqlSession.getMapper(clazz);
  }
}