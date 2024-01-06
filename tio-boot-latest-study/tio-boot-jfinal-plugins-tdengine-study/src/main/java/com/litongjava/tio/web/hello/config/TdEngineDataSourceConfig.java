package com.litongjava.tio.web.hello.config;

import com.litongjava.jfinal.aop.annotation.ABean;
import com.litongjava.jfinal.aop.annotation.AConfiguration;
import com.litongjava.tio.web.hello.config.utils.TDUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

@AConfiguration
public class TdEngineDataSourceConfig {
  
  @ABean(destroyMethod = "close", priority = 10)
  public DataSource hikariDataSource() {
    HikariConfig config = new HikariConfig();
    // jdbc properties
    String host = "192.168.3.9";
    int port = 6041;
    String user = "root";
    String pswd = "taosdata";
    String dbName = "test_ws_parabind";
    String driverClassName = "com.taosdata.jdbc.rs.RestfulDriver";
    // String driverClassName = "com.taosdata.jdbc.TSDBDriver";

    String jdbcUrl = getJdbcUrl(host, port, user, pswd, dbName);
    config.setJdbcUrl(jdbcUrl);
    config.setDriverClassName(driverClassName);
    // connection pool configurations
    config.setMinimumIdle(10); // minimum number of idle connection
    config.setMaximumPoolSize(10); // maximum number of connection in the pool
    config.setConnectionTimeout(30000); // maximum wait milliseconds for get connection from pool
    config.setMaxLifetime(0); // maximum life time for each connection
    config.setIdleTimeout(0); // max idle time for recycle idle connection
    config.setConnectionTestQuery("select server_status()"); // validation query

    HikariDataSource ds = new HikariDataSource(config); // create datasource
    TDUtils.setDataSource(ds);
    return ds;
  }

  private String getJdbcUrl(String host, int port, String user, String pswd, String dbName) {
    // 添加batchfetch=true属性后得到的Websocket连接
    return "jdbc:TAOS-RS://" + host + ":" + port + "/" + dbName + "?user=" + user + "&password=" + pswd
        + "&batchfetch=true";
  }
}
