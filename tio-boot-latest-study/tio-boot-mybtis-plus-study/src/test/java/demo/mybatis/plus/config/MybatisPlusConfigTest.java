package demo.mybatis.plus.config;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSessionManager;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;

import com.litongjava.tio.utils.environment.EnvironmentUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import demo.mybatis.plus.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MybatisPlusConfigTest {

  @Test
  public void test() {
    EnvironmentUtils.load("app.properties");
    String jdbcUrl = EnvironmentUtils.get("jdbc.url");
    String jdbcUser = EnvironmentUtils.get("jdbc.user");
    String jdbcPswd = EnvironmentUtils.get("jdbc.pswd");
    HikariConfig config = new HikariConfig();
    // 设定基本参数
    config.setJdbcUrl(jdbcUrl);
    config.setUsername(jdbcUser);
    config.setPassword(jdbcPswd);
    config.setMaximumPoolSize(2);
    HikariDataSource dataSource = new HikariDataSource(config);

    // environment
    Environment environment = new Environment("default", new JdbcTransactionFactory(), dataSource);
    // config
    org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
    configuration.setEnvironment(environment);
    configuration.addMappers("demo.mybatis.plus.mapper");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    SqlSessionManager sqlSessionManager = SqlSessionManager.newInstance(sqlSessionFactory);
    log.info("sqlSessionManager:{}", sqlSessionManager);
    UserMapper mapper = sqlSessionManager.openSession().getMapper(UserMapper.class);
    System.out.println(mapper);
    System.out.println(mapper.selectList(null));

  }

}
