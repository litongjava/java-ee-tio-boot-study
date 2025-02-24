package com.litongjava.spring.boot.tio.boot.demo01.config;

import com.litongjava.annotation.ABean;
import com.litongjava.annotation.AConfiguration;
import com.litongjava.db.activerecord.ActiveRecordPlugin;
import com.litongjava.db.activerecord.OrderedFieldContainerFactory;
import com.litongjava.db.druid.DruidPlugin;
import com.litongjava.jfinal.aop.Aop;

@AConfiguration
public class ActiveRecordPluginConfig {
  @ABean(priority = 10)
  public DruidPlugin druidPlugin() {
    String jdbcUrl = "jdbc:mysql://192.168.3.9:3306/mybatis_plus_study";
    String jdbcUser = "root";
    String jdbcPswd = "robot_123456#";

    DruidPlugin druidPlugin = new DruidPlugin(jdbcUrl, jdbcUser, jdbcPswd);
    druidPlugin.start();
    return druidPlugin;
  }

  @ABean
  public ActiveRecordPlugin activeRecordPlugin() {
    DruidPlugin druidPlugin = Aop.get(DruidPlugin.class);
    ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
    arp.setContainerFactory(new OrderedFieldContainerFactory());
    arp.start();
    return arp;
  }

}
