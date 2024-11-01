package com.litongjava.tio.web.hello.config;

import com.litongjava.annotation.AConfiguration;
import com.litongjava.annotation.Initialization;
import com.litongjava.tio.boot.http.handler.common.WebjarHandler;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.boot.swagger.SwaggerResourceHandler;
import com.litongjava.tio.boot.swagger.SwaggerV2ApiDocsHandler;
import com.litongjava.tio.boot.swagger.TioSwaggerV2Config;
import com.litongjava.tio.http.server.router.HttpRequestRouter;
import com.litongjava.tio.web.hello.controller.SwaggerUiHandler;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;

@AConfiguration
public class SwaggerConfiguration {

  @Initialization
  public void config() {
    TioBootServer server = TioBootServer.me();

    @SuppressWarnings("deprecation")
    ApiInfo appInfo = new ApiInfoBuilder().title("DemoAPI文档")
        //
        .description("Demo相关接口")
        //
        .contact("***@mail.com").version("1.0").build();

    TioSwaggerV2Config tioSwaggerV2Config = new TioSwaggerV2Config();
    tioSwaggerV2Config.setApiInfo(appInfo);
    tioSwaggerV2Config.setEnable(true);

    server.setSwaggerV2Config(tioSwaggerV2Config);

    HttpRequestRouter requestRouter = server.getRequestRouter();

    if (requestRouter != null) {
      SwaggerUiHandler swggerUiHander = new SwaggerUiHandler();
      requestRouter.add("/doc.html", swggerUiHander::html);
      
      WebjarHandler webjarHandler = new WebjarHandler();
      requestRouter.add("/webjars/**", webjarHandler::index);

      SwaggerResourceHandler swaggerResourceHandler = new SwaggerResourceHandler();
      requestRouter.add("/swagger-resources", swaggerResourceHandler::index);
      requestRouter.add("/swagger-resources/configuration/ui", swaggerResourceHandler::configurationUi);

      SwaggerV2ApiDocsHandler swaggerV2ApiDocsHandler = new SwaggerV2ApiDocsHandler();
      requestRouter.add("/v2/api-docs", swaggerV2ApiDocsHandler::index);
    }
  }

}
