package com.litongjava.tio.boot.demo.disruptor.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.litongjava.jfinal.aop.annotation.ABean;
import com.litongjava.jfinal.aop.annotation.AConfiguration;
import com.litongjava.tio.boot.demo.disruptor.factory.HelloEventFactory;
import com.litongjava.tio.boot.demo.disruptor.handler.HelloEventHandler;
import com.litongjava.tio.boot.demo.disruptor.model.MessageModel;
import com.litongjava.tio.boot.server.TioBootServer;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

@AConfiguration
public class DisruptorConfiguration {
  @ABean
  public RingBuffer<MessageModel> messageModelRingBuffer() {
    // 定义用于事件处理的线程池， Disruptor通过java.util.concurrent.ExecutorSerivce提供的线程来触发consumer的事件处理
    ExecutorService executor = Executors.newFixedThreadPool(2);
    // 指定事件工厂
    HelloEventFactory factory = new HelloEventFactory();
    // 指定ringbuffer字节大小，必须为2的N次方（能将求模运算转为位运算提高效率），否则将影响效率
    int bufferSize = 1024 * 256;

    BlockingWaitStrategy waitStrategy = new BlockingWaitStrategy();
    // 单线程模式，获取额外的性能
    @SuppressWarnings("deprecation")
    Disruptor<MessageModel> disruptor = new Disruptor<>(factory, bufferSize, executor, ProducerType.SINGLE,
        waitStrategy);
    // 设置事件业务处理器---消费者
    disruptor.handleEventsWith(new HelloEventHandler());
    // 启动disruptor线程
    disruptor.start();
    // 获取ringbuffer环，用于接取生产者生产的事件
    RingBuffer<MessageModel> ringBuffer = disruptor.getRingBuffer();
    // 关闭程序时关闭时disruptor
    TioBootServer.me().addDestroyMethod(disruptor::shutdown);
    return ringBuffer;
  }

}