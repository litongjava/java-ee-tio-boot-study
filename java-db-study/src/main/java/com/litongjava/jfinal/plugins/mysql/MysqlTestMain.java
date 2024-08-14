package com.litongjava.jfinal.plugins.mysql;

import java.util.List;

import com.litongjava.db.activerecord.ActiveRecordPlugin;
import com.litongjava.db.activerecord.Db;
import com.litongjava.db.activerecord.OrderedFieldContainerFactory;
import com.litongjava.db.activerecord.Record;
import com.litongjava.db.druid.DruidPlugin;

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
