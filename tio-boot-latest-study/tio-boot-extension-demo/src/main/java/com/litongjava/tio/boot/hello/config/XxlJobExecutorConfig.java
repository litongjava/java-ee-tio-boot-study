package com.litongjava.tio.boot.hello.config;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import com.litongjava.jfinal.aop.annotation.Bean;
import com.litongjava.jfinal.aop.annotation.Configuration;
import com.litongjava.tio.boot.hello.job.MyJobHandler;
import com.xxl.job.core.executor.XxlJobExecutor;
import com.xxl.job.core.executor.impl.XxlJobSimpleExecutor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Configuration
public class XxlJobExecutorConfig {

  @Bean(destroyMethod = "destroy")
  public XxlJobSimpleExecutor xxlJobSimpleExecutor() {
    // load executor prop
    Properties xxlJobProp = loadProperties("xxl-job-executor.properties");
    // init executor
    XxlJobSimpleExecutor xxlJobExecutor = new XxlJobSimpleExecutor();
    xxlJobExecutor.setAdminAddresses(xxlJobProp.getProperty("xxl.job.admin.addresses"));
    xxlJobExecutor.setAccessToken(xxlJobProp.getProperty("xxl.job.accessToken"));
    xxlJobExecutor.setAppname(xxlJobProp.getProperty("xxl.job.executor.appname"));
    xxlJobExecutor.setAddress(xxlJobProp.getProperty("xxl.job.executor.address"));
    xxlJobExecutor.setIp(xxlJobProp.getProperty("xxl.job.executor.ip"));
    xxlJobExecutor.setPort(Integer.valueOf(xxlJobProp.getProperty("xxl.job.executor.port")));
    xxlJobExecutor.setLogPath(xxlJobProp.getProperty("xxl.job.executor.logpath"));
    xxlJobExecutor.setLogRetentionDays(Integer.valueOf(xxlJobProp.getProperty("xxl.job.executor.logretentiondays")));
    // registry job bean
    // xxlJobExecutor.setXxlJobBeanList(Arrays.asList(new SyncXxlJob()));
    XxlJobExecutor.registJobHandler("my_job", new MyJobHandler());
    // start executor
    try {
      xxlJobExecutor.start();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    return xxlJobExecutor;
  }

  public static Properties loadProperties(String propertyFileName) {
    InputStreamReader in = null;
    try {
      ClassLoader loder = Thread.currentThread().getContextClassLoader();
      in = new InputStreamReader(loder.getResourceAsStream(propertyFileName), "UTF-8");
      if (in != null) {
        Properties prop = new Properties();
        prop.load(in);
        return prop;
      }
    } catch (IOException e) {
      log.error("load {} error!", propertyFileName);
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (IOException e) {
          log.error("close {} error!", propertyFileName);
        }
      }
    }
    return null;
  }
}