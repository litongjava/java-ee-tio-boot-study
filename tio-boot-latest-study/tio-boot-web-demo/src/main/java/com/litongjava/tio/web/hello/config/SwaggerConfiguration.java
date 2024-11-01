package com.litongjava.tio.web.hello.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.litongjava.annotation.AConfiguration;
import com.litongjava.annotation.Initialization;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@AConfiguration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfiguration {

  @Initialization
  public Docket createRestApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        //
        .enable(true).apiInfo(apiInfo())
        //
        .select().apis(RequestHandlerSelectors.basePackage("com.litongjava.tio.web.hello.controller"))
        //
        .paths(PathSelectors.any()).build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("小红书粉商工作台API文档")
        //
        .description("粉商工作台相关接口").termsOfServiceUrl("http://ip:7001/")
        //
        .contact("***@mail.com").version("1.0").build();
  }
}
