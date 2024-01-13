package com.litongjava.tio.web.hello;

import com.litongjava.jfinal.aop.annotation.AConfiguration;
import com.litongjava.jfinal.aop.annotation.AInitialization;
import com.litongjava.tio.boot.http.interceptor.HttpServerInterceptorModel;
import com.litongjava.tio.boot.satoken.SaTokenInterceptor;
import com.litongjava.tio.boot.server.TioBootServer;

@AConfiguration
public class InterceptorConfiguration {

  @AInitialization
  public void config() {
    SaTokenInterceptor saTokenInterceptor = new SaTokenInterceptor();
    HttpServerInterceptorModel model = new HttpServerInterceptorModel();
    model.setInterceptor(saTokenInterceptor);
    model.addblockeUrl("/**");
    model.addAlloweUrls("/register/*", "/auth/*");

    TioBootServer.getServerInteceptorConfigure().add(model);

  }
}
