package com.litongjava.spring.boot.tio.boot.demo01.config;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.jfinal.aop.annotation.ABean;
import com.litongjava.jfinal.aop.annotation.AConfiguration;
import com.litongjava.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.litongjava.jfinal.plugin.activerecord.OrderedFieldContainerFactory;
import com.litongjava.jfinal.plugin.druid.DruidPlugin;

@AConfiguration
public class ActiveRecordPluginConfig {
  @ABean(priority = 10,destroyMethod = "stop")
  public DruidPlugin druidPlugin() {
    String jdbcUrl = "jdbc:mysql://192.168.3.9:3306/mybatis_plus_study";
    String jdbcUser = "root";
    String jdbcPswd = "robot_123456#";

    DruidPlugin druidPlugin = new DruidPlugin(jdbcUrl, jdbcUser, jdbcPswd);
    druidPlugin.start();
    return druidPlugin;
  }

  @ABean(destroyMethod = "stop")
  public ActiveRecordPlugin activeRecordPlugin() {
    DruidPlugin druidPlugin = Aop.get(DruidPlugin.class);
    ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
    arp.setContainerFactory(new OrderedFieldContainerFactory());
    arp.start();
    return arp;
  }
}
