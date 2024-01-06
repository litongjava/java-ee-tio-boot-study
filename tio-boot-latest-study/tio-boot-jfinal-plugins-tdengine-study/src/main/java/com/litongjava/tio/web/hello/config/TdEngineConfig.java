package com.litongjava.tio.web.hello.config;

import com.litongjava.data.utils.TioRequestParamUtils;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.jfinal.aop.annotation.ABean;
import com.litongjava.jfinal.aop.annotation.AConfiguration;
import com.litongjava.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.litongjava.jfinal.plugin.activerecord.OrderedFieldContainerFactory;
import com.litongjava.tio.utils.environment.EnvironmentUtils;
import com.litongjava.tio.web.hello.config.utils.TDUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@AConfiguration
public class TdEngineConfig {
  @ABean(destroyMethod = "close", priority = 10)
  public HikariDataSource hikariDataSource() {
    HikariConfig config = new HikariConfig();
    // jdbc properties
    String host = "192.168.3.9";
    int port = 6041;
    String user = "root";
    String pswd = "taosdata";
    String driverClassName = "com.taosdata.jdbc.rs.RestfulDriver";
    //String driverClassName = "com.taosdata.jdbc.TSDBDriver";
    
    // 添加batchfetch=true属性后得到的Websocket连接
    String jdbcUrl = "jdbc:TAOS-RS://" + host + ":" + port + "?user=" + user + "&password=" + pswd + "&batchfetch=true";
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

//  @ABean(destroyMethod = "stop")
//  public ActiveRecordPlugin activeRecordPlugin() {
//    boolean showSql = EnvironmentUtils.getBoolean("jdbc.showSql", false);
//
//    HikariDataSource dataSource = Aop.get(HikariDataSource.class);
//    ActiveRecordPlugin arp = new ActiveRecordPlugin(dataSource);
//    arp.setContainerFactory(new OrderedFieldContainerFactory());
//    arp.setShowSql(showSql);
//
//    // add
//    TioRequestParamUtils.types.add("bigint");
//    arp.start();
//    return arp;
//  }
}
