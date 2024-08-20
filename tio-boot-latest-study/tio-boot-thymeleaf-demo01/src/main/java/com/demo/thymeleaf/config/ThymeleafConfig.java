package com.demo.thymeleaf.config;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.litongjava.jfinal.aop.annotation.AConfiguration;
import com.litongjava.jfinal.aop.annotation.AInitialization;
import com.litongjava.tio.utils.thymeleaf.Thymyleaf;
import com.litongjava.tio.utils.thymeleaf.ThymyleafEngine;

@AConfiguration
public class ThymeleafConfig {

  @AInitialization
  public void config() {
    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    templateResolver.setPrefix("templates/");
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode("HTML");
    templateResolver.setCharacterEncoding("UTF-8");

    TemplateEngine templateEngine = new TemplateEngine();
    templateEngine.setTemplateResolver(templateResolver);

    Thymyleaf.add(new ThymyleafEngine(templateEngine));
  }
}
