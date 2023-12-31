package com.litongjava.jfinal.plugins.mysql;

import com.litongjava.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.litongjava.jfinal.plugin.activerecord.Db;
import com.litongjava.jfinal.plugin.activerecord.OrderedFieldContainerFactory;
import com.litongjava.jfinal.plugin.activerecord.Record;
import com.litongjava.jfinal.plugin.druid.DruidPlugin;

import java.util.List;

/**
 * Created by litonglinux@qq.com on 12/30/2023_12:48 AM
 */
public class MysqlTestMain {
  public static void main(String[] args) {
    String jdbcUrl = "jdbc:mysql://192.168.3.9:3306/mybatis_plus_study";
    String jdbcUser = "root";
    String jdbcPswd = "robot_123456#";

    DruidPlugin druidPlugin = new DruidPlugin(jdbcUrl, jdbcUser, jdbcPswd);

    ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
    arp.setContainerFactory(new OrderedFieldContainerFactory());

    druidPlugin.start();
    arp.start();

    List<Record> records = Db.findAll("USER");
    System.out.println(records.size());



  }
}
