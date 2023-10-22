package com.litongjava.tio.boot.hello.config;

import org.junit.Before;
import org.junit.Test;
import org.tio.utils.jfinal.P;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.OrderedFieldContainerFactory;
import com.jfinal.plugin.hikaricp.HikariCpPlugin;
import com.jfinal.template.Engine;
import com.jfinal.template.source.ClassPathSourceFactory;

public class TableToJsonConfigTest {

  @Before
  public void before() {
    P.use("app.properties");
  }
  @Test
  public void test() {
    String jdbcUrl = P.get("jdbc.url");
    String jdbcUser = P.get("jdbc.user");
    
    String jdbcPswd = P.get("jdbc.pswd");
    HikariCpPlugin dp = new HikariCpPlugin(jdbcUrl, jdbcUser, jdbcPswd);
    dp.start();

    System.out.println("=====================================");
    String property = P.get("tio.mode");
    
    ActiveRecordPlugin arp = new ActiveRecordPlugin(dp.getDataSource());
    arp.setContainerFactory(new OrderedFieldContainerFactory());
    if ("dev".equals(property)) {
      arp.setDevMode(true);
    }

    Engine engine = arp.getEngine();
    engine.setSourceFactory(new ClassPathSourceFactory());
    engine.setCompressorOn(' ');
    engine.setCompressorOn('\n');
    //arp.addSqlTemplate("/sql/all_sqls.sql");
    arp.start();
    try {
      Thread.sleep(10*1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
 
  }

}
