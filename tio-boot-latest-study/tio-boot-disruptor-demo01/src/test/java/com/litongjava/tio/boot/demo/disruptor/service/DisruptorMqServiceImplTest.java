package com.litongjava.tio.boot.demo.disruptor.service;

import org.junit.Before;
import org.junit.Test;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.tio.boot.demo.disruptor.HelloApp;
import com.litongjava.tio.boot.tesing.TioBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DisruptorMqServiceImplTest {

  @Before
  public void before() throws Exception {
    //TioBootTest.before();
    TioBootTest.scan(HelloApp.class);
  }

  /**
   * 项目内部使用Disruptor做消息队列
   * @throws Exception
   */
  @Test
  public void sayHelloMqTest() throws Exception {
    DisruptorMqService disruptorMqService = Aop.get(DisruptorMqService.class);
    // send
    disruptorMqService.sayHelloMq("消息到了，Hello world!");
    log.info("消息队列已发送完毕");
    // 这里停止2000ms是为了确定是处理消息是异步的
    Thread.sleep(2000);
  }

}
