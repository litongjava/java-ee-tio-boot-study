package com.litongjava.tio.web.hello.config;

import javax.sql.DataSource;

import com.litongjava.data.utils.TioRequestParamUtils;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.jfinal.aop.annotation.ABean;
import com.litongjava.jfinal.aop.annotation.AConfiguration;
import com.litongjava.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.litongjava.jfinal.plugin.activerecord.OrderedFieldContainerFactory;
import com.litongjava.tio.utils.environment.EnvironmentUtils;

@AConfiguration
public class ActiveRecordPluginConfig {

  @ABean(destroyMethod = "stop")
  public ActiveRecordPlugin activeRecordPlugin() {
    boolean showSql = EnvironmentUtils.getBoolean("jdbc.showSql", false);

    DataSource dataSource = Aop.get(DataSource.class);
    ActiveRecordPlugin arp = new ActiveRecordPlugin(dataSource);
    arp.setContainerFactory(new OrderedFieldContainerFactory());
    arp.setShowSql(showSql);

    // add
    TioRequestParamUtils.types.add("bigint");
    arp.start();
    return arp;
  }
}
