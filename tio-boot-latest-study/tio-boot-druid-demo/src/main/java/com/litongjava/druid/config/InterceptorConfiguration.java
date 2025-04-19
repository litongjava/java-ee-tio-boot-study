package com.litongjava.druid.config;

import com.litongjava.annotation.AConfiguration;
import com.litongjava.annotation.Initialization;
import com.litongjava.tio.boot.druid.DruidWebStatInterceptor;
import com.litongjava.tio.boot.http.interceptor.HttpInteceptorConfigure;
import com.litongjava.tio.boot.http.interceptor.HttpInterceptorModel;
import com.litongjava.tio.boot.server.TioBootServer;

@AConfiguration
public class InterceptorConfiguration {

  @Initialization
  public void configureInterceptors() {
    // 1. 在启动/配置类里
    String contextPath = ""; // 如果你的服务没有上下文路径可以传空
    String exclusionsCsv = "*.js,*.css,*.png,*.jpg,/druid/*";
    boolean profileEnable = true; // 如果不需要调用耗时分析，可传 false

    DruidWebStatInterceptor webStatInterceptor = new DruidWebStatInterceptor(contextPath, exclusionsCsv, profileEnable);

    // 构造一个 InterceptorModel
    HttpInterceptorModel model = new HttpInterceptorModel().setName("druid-web-stat")
        // 拦截所有请求
        .addBlockUrl("/**")
        // 但放行静态资源 & druid 面板自身
        .addAllowUrls("*.js", "*.css", "*.png", "*.jpg", "*.ico", "/druid/**").setInterceptor(webStatInterceptor);

    // 注入到全局配置
    HttpInteceptorConfigure cfg = new HttpInteceptorConfigure();
    cfg.add(model);

    // 将配置给 Server
    TioBootServer.me().setHttpInteceptorConfigure(cfg);

  }
}
