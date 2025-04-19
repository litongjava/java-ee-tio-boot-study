package com.litongjava.druid.config;

import java.util.Arrays;
import java.util.Collections;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.litongjava.annotation.AConfiguration;
import com.litongjava.annotation.Initialization;
import com.litongjava.db.activerecord.ActiveRecordPlugin;
import com.litongjava.db.activerecord.Db;
import com.litongjava.db.activerecord.OrderedFieldContainerFactory;
import com.litongjava.db.activerecord.Row;
import com.litongjava.db.activerecord.dialect.PostgreSqlDialect;
import com.litongjava.db.druid.DruidPlugin;
import com.litongjava.tio.boot.druid.DruidConfig;
import com.litongjava.tio.boot.druid.DruidStatHandler;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.http.server.router.HttpRequestRouter;
import com.litongjava.tio.utils.environment.EnvUtils;

import lombok.extern.slf4j.Slf4j;

@AConfiguration
@Slf4j
public class AdminAppConfig {

  @Initialization
  public void config() {
    // —— 一、配置 Druid 连接池并加上 StatFilter/WallFilter —— 
    String jdbcUrl = EnvUtils.getStr("jdbc.url");
    String jdbcUser = EnvUtils.getStr("jdbc.user");
    String jdbcPwd = EnvUtils.getStr("jdbc.pswd");

    DruidPlugin druidPlugin = new DruidPlugin(jdbcUrl, jdbcUser, jdbcPwd);

    // 1）防 SQL 注入
    WallFilter wall = new WallFilter();
    wall.setDbType("postgresql");
    druidPlugin.addFilter(wall);

    // 2）统计 & 慢 SQL 日志
    StatFilter stat = new StatFilter();
    stat.setSlowSqlMillis(500); // 慢 SQL 阈值 500ms
    stat.setLogSlowSql(true);
    stat.setMergeSql(true); // 合并相同 SQL
    druidPlugin.addFilter(stat);

    // 启动
    druidPlugin.start();

    // 再注册 AR、跑一次测试
    ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
    arp.setContainerFactory(new OrderedFieldContainerFactory());
    arp.setDialect(new PostgreSqlDialect());
    arp.start();

    // 执行一条查询，保证 Druid 已经有统计数据
    Db.findAll("tio_boot_admin_system_upload_file").forEach((Row r) -> {
      /* no-op */ });

    // —— 二、配置 Druid HTTP 监控面板 —— 
    DruidConfig druidConfig = new DruidConfig();
    // 登录账号/密码（你可以自己改）
    druidConfig.setLoginUsername("admin");
    druidConfig.setLoginPassword("123456");
    // 是否允许页面“Reset All”
    druidConfig.setResetEnable(true);
    // 只允许本机访问监控页
    druidConfig.setAllowIps(Arrays.asList("127.0.0.1"));
    druidConfig.setDenyIps(Collections.emptyList());
    // 如果你想通过 JMX 远程拉数据，可以填下面这几项：
    // druidConfig.setJmxUrl("service:jmx:rmi:///jndi/rmi://localhost:8848/jmxrmi");
    // druidConfig.setJmxUsername("jmxUser");
    // druidConfig.setJmxPassword("jmxPwd");

    // 注册路由
    TioBootServer me = TioBootServer.me();
    HttpRequestRouter router = me.getRequestRouter();
    DruidStatHandler druidPanel = new DruidStatHandler(druidConfig);
    // 注意要用 /** 通配
    router.add("/druid/**", druidPanel::handle);

    log.info(">>> Druid stat view is up at http://localhost/druid/index.html");
  }
}
