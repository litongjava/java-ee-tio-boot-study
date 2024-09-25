package com.litongjava.jfinal.plugins.postgres;

import com.litongjava.db.activerecord.ActiveRecordPlugin;
import com.litongjava.db.activerecord.Db;
import com.litongjava.db.activerecord.OrderedFieldContainerFactory;
import com.litongjava.db.hikaricp.HikariCpPlugin;

public class PostgresTestMain {
  public static void main(String[] args) {
    String jdbcUrl = "jdbc:postgresql://192.168.3.9/defaultdb";
    String jdbcUser = "postgres";
    String jdbcPswd = "123456";

    HikariCpPlugin hikariCpPlugin = new HikariCpPlugin(jdbcUrl, jdbcUser, jdbcPswd);

    ActiveRecordPlugin arp = new ActiveRecordPlugin(hikariCpPlugin);
    arp.setContainerFactory(new OrderedFieldContainerFactory());

    hikariCpPlugin.start();
    arp.start();

    for (int i = 0; i < 2; i++) {
      Db.queryInt("select 1");
    }

  }
}
