package com.litongjava.mica.mqtt.client.config;

import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.TimerTask;

import org.tio.core.ChannelContext;

import com.litongjava.jfinal.aop.annotation.Bean;
import com.litongjava.jfinal.aop.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import net.dreamlu.iot.mqtt.codec.MqttPublishMessage;
import net.dreamlu.iot.mqtt.codec.MqttQoS;
import net.dreamlu.iot.mqtt.core.client.IMqttClientMessageListener;
import net.dreamlu.iot.mqtt.core.client.MqttClient;
import net.dreamlu.iot.mqtt.core.client.MqttClientCreator;

@Configuration
@Slf4j
public class MicaMQTTClientConfig {

  @Bean
  public MqttClient MqttClient() {
    // 初始化 mqtt 客户端
    MqttClientCreator creator = MqttClient.create().ip("192.168.3.9").port(1883).username("mica").password("mica");
    // 连接监听
    MqttClient client = creator.connectListener(new MqttClientConnectListener()).willMessage(builder -> {
      builder.topic("/test/offline").messageText("down").retain(false).qos(MqttQoS.AT_MOST_ONCE); // 遗嘱消息
    })
        // 同步连接，也可以使用 connect() 异步（可以避免 broker 没启动照成启动卡住），但是下面的订阅和发布可能还没连接成功。
        .connectSync();

    // 订阅
    client.subQos0("/test/123", new IMqttClientMessageListener() {
      @Override
      public void onSubscribed(ChannelContext context, String topicFilter, MqttQoS mqttQoS) {
        // 订阅成功之后触发，可在此处做一些业务逻辑
        log.info("topicFilter:{} MqttQoS:{} Subscription successful", topicFilter, mqttQoS);
      }

      @Override
      public void onMessage(ChannelContext context, String topic, MqttPublishMessage message, byte[] payload) {
        log.info(topic + '\t' + new String(payload, StandardCharsets.UTF_8));
      }
    });

    // 发送
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        client.publish("/test/client", "hello this is mica client".getBytes(StandardCharsets.UTF_8));
      }
    }, 1000, 2000);

    return client;
  }
}
