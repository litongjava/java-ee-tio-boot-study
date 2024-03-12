package com.litongjava.tio.boot.demo.disruptor.service;

import com.litongjava.jfinal.aop.annotation.AAutowired;
import com.litongjava.jfinal.aop.annotation.AService;
import com.litongjava.tio.boot.demo.disruptor.model.MessageModel;
import com.lmax.disruptor.RingBuffer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@AService
public class DisruptorMqServiceImpl implements DisruptorMqService {

  @AAutowired
  private RingBuffer<MessageModel> messageModelRingBuffer;

  public void sayHelloMq(String message) {
    log.info("record the message: {}", message);
    // 获取下一个Event槽的下标
    long sequence = messageModelRingBuffer.next();
    try {
      // 给Event填充数据
      MessageModel event = messageModelRingBuffer.get(sequence);
      event.setMessage(message);
      log.info("往消息队列中添加消息：{}", event);
    } catch (Exception e) {
      log.error("failed to add event to messageModelRingBuffer for : e = {},{}", e, e.getMessage());
    } finally {
      // 发布Event，激活观察者去消费，将sequence传递给改消费者
      // 注意最后的publish方法必须放在finally中以确保必须得到调用；如果某个请求的sequence未被提交将会堵塞后续的发布操作或者其他的producer
      messageModelRingBuffer.publish(sequence);
    }
  }
}