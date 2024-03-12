package com.litongjava.tio.boot.demo.disruptor.factory;

import com.litongjava.tio.boot.demo.disruptor.model.MessageModel;
import com.lmax.disruptor.EventFactory;

public class HelloEventFactory implements EventFactory<MessageModel> {

  public MessageModel newInstance() {
    return new MessageModel();
  }
}