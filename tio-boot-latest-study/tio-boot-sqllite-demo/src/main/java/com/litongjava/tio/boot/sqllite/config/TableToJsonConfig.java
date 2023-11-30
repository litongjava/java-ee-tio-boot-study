package com.litongjava.tio.boot.sqllite.config;

import javax.sql.DataSource;

import org.tio.utils.jfinal.P;

import com.alibaba.druid.pool.DruidDataSource;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.OrderedFieldContainerFactory;
import com.jfinal.plugin.activerecord.dialect.Sqlite3Dialect;
import com.jfinal.template.Engine;
import com.jfinal.template.source.ClassPathSourceFactory;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.jfinal.aop.annotation.Bean;
import com.litongjava.jfinal.aop.annotation.Configuration;
import com.litongjava.tio.boot.context.Enviorment;

@Configuration
public class TableToJsonConfig {

  private Enviorment enviorment = Aop.get(Enviorment.class);

//  @Bean(priority = 0)
//  public DataSource dataSource() {
//
//    String jdbcDriverClass = enviorment.get("jdbc.driverClass");
//    String jdbcUrl = enviorment.get("jdbc.url");
//    String jdbcUser = enviorment.get("jdbc.user");
//    String jdbcPswd = enviorment.get("jdbc.pswd");
//
//    HikariConfig config = new HikariConfig();
//    // 设定基本参数
//    config.setDriverClassName(jdbcDriverClass);
//    config.setJdbcUrl(jdbcUrl);
//    config.setUsername(jdbcUser);
//    config.setPassword(jdbcPswd);
////    config.setMaximumPoolSize(2);
//
//    return new HikariDataSource(config);
//  }

  @Bean(priority = 0, destroyMethod = "close")
  public DataSource dataSource() {
    String jdbcUrl = enviorment.get("jdbc.url");
    String jdbcUser = enviorment.get("jdbc.user");
    String jdbcPswd = enviorment.get("jdbc.pswd");

    DruidDataSource druidDataSource = new DruidDataSource();
    // 设定基本参数
    druidDataSource.setUrl(jdbcUrl);
    druidDataSource.setUsername(jdbcUser);
    druidDataSource.setPassword(jdbcPswd);
//    druidDataSource.close();
    return druidDataSource;
  }

  @Bean(destroyMethod = "stop", initMethod = "start")
  public ActiveRecordPlugin activeRecordPlugin() throws Exception {
    DataSource dataSource = Aop.get(DataSource.class);
    String property = P.get("tio.mode");
    ActiveRecordPlugin arp = new ActiveRecordPlugin(dataSource);
    arp.setDialect(new Sqlite3Dialect());
    // arp.setShowSql(enviorment.getBoolean("jdbc.showSql"));
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