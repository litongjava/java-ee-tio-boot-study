package demo.mybatis.plus.config;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionManager;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.litongjava.jfinal.aop.Autowired;
import com.litongjava.jfinal.aop.annotation.Bean;
import com.litongjava.jfinal.aop.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class MybatisPlusConfig {

  @Autowired
  private DataSource dataSource;

  /**
   * 获取SqlSession
   *
   * @return
   */
  @Bean(destroyMethod = "close")
  public SqlSessionManager getSqlSession() {
    // environment
    Environment environment = new Environment("default", new JdbcTransactionFactory(), dataSource);
    // MybatisConfiguration
    org.apache.ibatis.session.Configuration configuration = new MybatisConfiguration();
    configuration.setEnvironment(environment);
    configuration.addMappers("demo.mybatis.plus.mapper");

    // MybatisSqlSessionFactoryBuilder
    MybatisSqlSessionFactoryBuilder builder = new MybatisSqlSessionFactoryBuilder();

    log.info("builder:{}", builder);

    SqlSessionFactory sqlSessionFactory = builder.build(configuration);
    SqlSessionManager sqlSessionManager = SqlSessionManager.newInstance(sqlSessionFactory);
    return sqlSessionManager;
  }

}
