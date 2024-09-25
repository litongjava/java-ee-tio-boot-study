package com.litongjava.spring.boot.tio.boot.demo01.config;

import com.litongjava.db.activerecord.ActiveRecordPlugin;
import com.litongjava.db.activerecord.OrderedFieldContainerFactory;
import com.litongjava.db.druid.DruidPlugin;
import com.litongjava.jfinal.aop.annotation.AConfiguration;
import com.litongjava.tio.boot.server.TioBootServer;

@AConfiguration
public class ActiveRecordPluginConfig {
  
  public void activeRecordPlugin() {
    String jdbcUrl = "jdbc:mysql://192.168.3.9:3306/mybatis_plus_study";
    String jdbcUser = "root";
    String jdbcPswd = "robot_123456#";

    DruidPlugin druidPlugin = new DruidPlugin(jdbcUrl, jdbcUser, jdbcPswd);
    druidPlugin.start();

    ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
    arp.setContainerFactory(new OrderedFieldContainerFactory());
    arp.start();
    TioBootServer.me().addDestroyMethod(()->{
      druidPlugin.stop();
      arp.stop();
    });
  }
}
