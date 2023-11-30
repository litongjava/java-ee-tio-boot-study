package com.litongjava.tio.boot.hello.config;

import javax.sql.DataSource;

import org.tio.utils.jfinal.P;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.OrderedFieldContainerFactory;
import com.jfinal.template.Engine;
import com.jfinal.template.source.ClassPathSourceFactory;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.jfinal.aop.annotation.Bean;
import com.litongjava.jfinal.aop.annotation.Configuration;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class TableToJsonConfig {

  /**
   * config datasource
   * @return
   */
  @Bean(priority = 1)
  public DataSource dataSource() {
    String jdbcUrl = P.get("jdbc.url");
    String jdbcUser = P.get("jdbc.user");

    String jdbcPswd = P.get("jdbc.pswd");

    HikariConfig config = new HikariConfig();
    // 设定基本参数
    config.setJdbcUrl(jdbcUrl);
    config.setUsername(jdbcUser);
    config.setPassword(jdbcPswd);
//    config.setMaximumPoolSize(2);

    return new HikariDataSource(config);
  }

  /**
   * config ActiveRecordPlugin
   * @return
   * @throws Exception
   */
  @Bean(destroyMethod = "stop", initMethod = "start")
  public ActiveRecordPlugin activeRecordPlugin() throws Exception {
    DataSource dataSource = Aop.get(DataSource.class);
    String property = P.get("tio.mode");
    ActiveRecordPlugin arp = new ActiveRecordPlugin(dataSource);
    arp.setContainerFactory(new OrderedFieldContainerFactory());
    if ("dev".equals(property)) {
      arp.setDevMode(true);
    }

    Engine engine = arp.getEngine();
    engine.setSourceFactory(new ClassPathSourceFactory());
    engine.setCompressorOn(' ');
    engine.setCompressorOn('\n');
    // arp.addSqlTemplate("/sql/all_sqls.sql");
//    arp.start();
    return arp;
  }
}