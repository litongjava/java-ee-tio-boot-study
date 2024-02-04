package com.litongjava.tio.web.hello.config;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.litongjava.jfinal.aop.InterceptorManager;
import com.litongjava.jfinal.aop.annotation.AConfiguration;
import com.litongjava.jfinal.aop.annotation.AInitialization;
import com.litongjava.jfinal.proxy.ProxyManager;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.utils.environment.EnvironmentUtils;
import com.litongjava.tio.web.hello.utils.MqttClientUtils;

import lombok.extern.slf4j.Slf4j;

@AConfiguration
@Slf4j
public class EmqxClientConfig {
  @AInitialization
  public void config() {
    ProxyManager.me().setProxyFactory(null)
    String broker = EnvironmentUtils.get("emqx.broker");
    String username = EnvironmentUtils.get("emqx.username");
    String password = EnvironmentUtils.get("emqx.password");
    String topic = EnvironmentUtils.get("emqx.topic");

    int qos = EnvironmentUtils.getInt("emqx.qos", 2);

    String clientId = MqttClient.generateClientId();
    // 持久化
    MemoryPersistence persistence = new MemoryPersistence();
    // MQTT 连接选项
    MqttConnectOptions connOpts = new MqttConnectOptions();
    // 设置认证信息
    connOpts.setUserName(username);
    connOpts.setPassword(password.toCharArray());
    try {
      MqttClient client = new MqttClient(broker, clientId, persistence);
      // 设置回调
      client.setCallback(new SampleCallback());
      // 建立连接
      log.info("Connecting to broker: " + broker);
      client.connect(connOpts);
      log.info("Connected to broker: " + broker);
      // 订阅 topic
      client.subscribe(topic, qos);
      log.info("Subscribed to topic: " + topic);

      TioBootServer.addDestroyMethod(() -> {
        try {
          client.disconnect();
          log.info("Disconnected");
          client.close();
        } catch (MqttException e) {
          e.printStackTrace();
        }
      });
      
      MqttClientUtils.init(client,topic,qos);

    } catch (MqttException me) {
      log.error("reason " + me.getReasonCode());
      log.error("msg " + me.getMessage());
      log.error("loc " + me.getLocalizedMessage());
      log.error("cause " + me.getCause());
      log.error("excep " + me);
      me.printStackTrace();
    }
  }
}