package com.litongjava.tio.boot.postgresql.demo.config;

import javax.sql.DataSource;

import com.jfinal.template.Engine;
import com.jfinal.template.source.ClassPathSourceFactory;
import com.litongjava.annotation.AConfiguration;
import com.litongjava.annotation.Initialization;
import com.litongjava.db.activerecord.ActiveRecordPlugin;
import com.litongjava.db.activerecord.OrderedFieldContainerFactory;
import com.litongjava.db.activerecord.dialect.PostgreSqlDialect;
import com.litongjava.tio.utils.environment.EnvUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@AConfiguration
public class DbConfig {

  @Initialization
  public ActiveRecordPlugin config() {

    String jdbcUrl = EnvUtils.get("jdbc.url");
    String jdbcUser = EnvUtils.get("jdbc.user");

    String jdbcPswd = EnvUtils.get("jdbc.pswd");

    HikariConfig config = new HikariConfig();
    // 设定基本参数
    config.setJdbcUrl(jdbcUrl);
    config.setUsername(jdbcUser);
    config.setPassword(jdbcPswd);
    //    config.setMaximumPoolSize(2);

    DataSource dataSource = new HikariDataSource(config);

    ActiveRecordPlugin arp = new ActiveRecordPlugin(dataSource);
    arp.setContainerFactory(new OrderedFieldContainerFactory());
    arp.setDialect(new PostgreSqlDialect());
    if (EnvUtils.isDev()) {
      arp.setDevMode(true);
    }

    Engine engine = arp.getEngine();
    engine.setSourceFactory(new ClassPathSourceFactory());
    engine.setCompressorOn(' ');
    engine.setCompressorOn('\n');
    // arp.addSqlTemplate("/sql/all_sqls.sql");
    arp.start();
    return arp;
  }
}