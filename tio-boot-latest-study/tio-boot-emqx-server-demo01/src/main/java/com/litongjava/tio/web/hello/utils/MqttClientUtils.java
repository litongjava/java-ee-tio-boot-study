package com.litongjava.tio.web.hello.utils;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttClientUtils {
  private static MqttClient client;
  private static String topic;
  private static int qos;

  public static void init(MqttClient client, String topic, int qos) {
    MqttClientUtils.client = client;
    MqttClientUtils.topic = topic;
    MqttClientUtils.qos = qos;
  }

  public static MqttClient getClient() {
    return client;
  }

  public static void publish(MqttMessage message) {
    message.setQos(qos);
    try {
      client.publish(topic, message);
    } catch (MqttException e) {
      e.printStackTrace();
    }
  }

  public static void publishWithQos(MqttMessage message) {
    try {
      client.publish(topic, message);
    } catch (MqttException e) {
      e.printStackTrace();
    }
  }
}
