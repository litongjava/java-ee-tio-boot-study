package demo.mybatis.plus.config;

import javax.sql.DataSource;

import com.litongjava.jfinal.aop.annotation.Bean;
import com.litongjava.jfinal.aop.annotation.Configuration;
import com.litongjava.tio.utils.environment.EnvironmentUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {

  @Bean(priority = 1)
  public DataSource dataSource() {
    String jdbcUrl = EnvironmentUtils.get("jdbc.url");
    String jdbcUser = EnvironmentUtils.get("jdbc.user");
    String jdbcPswd = EnvironmentUtils.get("jdbc.pswd");
    HikariConfig config = new HikariConfig();
    // 设定基本参数
    config.setJdbcUrl(jdbcUrl);
    config.setUsername(jdbcUser);
    config.setPassword(jdbcPswd);
    config.setMaximumPoolSize(2);
    return new HikariDataSource(config);
  }
}
