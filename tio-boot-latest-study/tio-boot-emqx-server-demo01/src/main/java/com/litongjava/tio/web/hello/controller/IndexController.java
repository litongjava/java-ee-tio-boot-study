package com.litongjava.tio.web.hello.controller;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.web.hello.utils.MqttClientUtils;

@RequestPath("/")
public class IndexController {
  @RequestPath()
  public String index() {
    String content = "Hello World";
    MqttMessage message = new MqttMessage(content.getBytes());
    MqttClientUtils.publish(message);
    return "index";
  }
}

