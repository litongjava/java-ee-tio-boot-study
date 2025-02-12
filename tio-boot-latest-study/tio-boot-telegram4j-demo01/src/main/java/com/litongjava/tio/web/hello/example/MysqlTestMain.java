package com.litongjava.tio.web.hello.example;

import java.util.List;

import com.litongjava.db.activerecord.ActiveRecordPlugin;
import com.litongjava.db.activerecord.Db;
import com.litongjava.db.activerecord.OrderedFieldContainerFactory;
import com.litongjava.db.activerecord.Row;
import com.litongjava.db.druid.DruidPlugin;

public class MysqlTestMain {
  public static void main(String[] args) {
    // 数据库配置信息
    String jdbcUrl = "jdbc:mysql://192.168.3.9:3306/mybatis_plus_study";
    String jdbcUser = "root";
    String jdbcPswd = "robot_123456#";

    // 初始化 DruidPlugin 数据库连接池插件
    DruidPlugin druidPlugin = new DruidPlugin(jdbcUrl, jdbcUser, jdbcPswd);

    // 初始化 ActiveRecordPlugin 数据库操作插件
    ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
    arp.setContainerFactory(new OrderedFieldContainerFactory());

    // 启动插件
    druidPlugin.start();
    arp.start();

    // 查询 "USER" 表中的数据并打印记录数
    List<Row> records = Db.findAll("USER");
    System.out.println("记录数量: " + records.size());
  }
}