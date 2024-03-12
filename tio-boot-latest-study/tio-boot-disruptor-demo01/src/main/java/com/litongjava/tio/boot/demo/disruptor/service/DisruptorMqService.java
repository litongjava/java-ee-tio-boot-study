package com.litongjava.tio.boot.demo.disruptor.service;

public interface DisruptorMqService {
  /**
   * 消息
   * @param message
   */
  void sayHelloMq(String message);
}