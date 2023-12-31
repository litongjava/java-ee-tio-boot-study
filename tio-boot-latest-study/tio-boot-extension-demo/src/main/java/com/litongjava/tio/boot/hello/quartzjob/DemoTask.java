package com.litongjava.tio.boot.hello.quartzjob;

import org.quartz.JobExecutionContext;

import com.litongjava.tio.utils.quartz.AbstractJobWithLog;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoTask extends AbstractJobWithLog {
  @Override
  public void run(JobExecutionContext context) throws Exception {
    log.info("hello");
    log.info("context:{}", context);
  }
}