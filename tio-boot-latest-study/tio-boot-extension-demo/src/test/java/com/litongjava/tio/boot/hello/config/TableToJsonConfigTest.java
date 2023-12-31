package com.litongjava.tio.boot.hello.config;

import org.junit.Before;
import org.junit.Test;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.OrderedFieldContainerFactory;
import com.jfinal.plugin.hikaricp.HikariCpPlugin;
import com.jfinal.template.Engine;
import com.jfinal.template.source.ClassPathSourceFactory;
import com.litongjava.tio.utils.environment.PropUtils;

public class TableToJsonConfigTest {

  @Before
  public void before() {
    PropUtils.use("app.properties");
  }
  @Test
  public void test() {
    String jdbcUrl = PropUtils.get("jdbc.url");
    String jdbcUser = PropUtils.get("jdbc.user");
    
    String jdbcPswd = PropUtils.get("jdbc.pswd");
    HikariCpPlugin dp = new HikariCpPlugin(jdbcUrl, jdbcUser, jdbcPswd);
    dp.start();

    System.out.println("=====================================");
    String property = PropUtils.get("devMode");
    
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
