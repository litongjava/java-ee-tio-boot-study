package com.litongjava.test.config;

import com.jfinal.template.Engine;
import com.jfinal.template.source.FileSourceFactory;
import com.litongjava.annotation.AConfiguration;
import com.litongjava.annotation.Initialization;
import com.litongjava.tio.utils.environment.EnvUtils;

@AConfiguration
public class EnjoyEngineConfig {

  private final String RESOURCE_BASE_PATH = EnvUtils.get("template.base.path");

  @Initialization
  public Engine engine() {
    Engine engine = Engine.use();
    engine.setBaseTemplatePath(RESOURCE_BASE_PATH);
    // engine.setToClassPathSourceFactory();
    engine.setSourceFactory(new FileSourceFactory());
    // 支持模板热加载，绝大多数生产环境下也建议配置成 true，除非是极端高性能的场景
    engine.setDevMode(true);
    // 配置极速模式，性能提升 13%
    Engine.setFastMode(true);
    // JFinal 4.9.02 新增配置：支持中文表达式、中文变量名、中文方法名、中文模板函数名
    Engine.setChineseExpression(true);
    return engine;
  }
}
