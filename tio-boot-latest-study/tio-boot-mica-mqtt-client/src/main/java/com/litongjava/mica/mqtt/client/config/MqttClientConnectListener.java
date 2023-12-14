package com.litongjava.mica.mqtt.client.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;

import net.dreamlu.iot.mqtt.core.client.IMqttClientConnectListener;

/**
 * Client Connection Status Listening
 */
public class MqttClientConnectListener implements IMqttClientConnectListener {
  private static final Logger logger = LoggerFactory.getLogger(MqttClientConnectListener.class);

  @Override
  public void onConnected(ChannelContext context, boolean isReconnect) {
    if (isReconnect) {
      logger.info("Reconnect mqtt server reconnected successfully");
    } else {
      logger.info("Connection to mqtt server successful");
    }
  }

  @Override
  public void onDisconnect(ChannelContext channelContext, Throwable throwable, String remark, boolean isRemove) {
    logger.error("mqtt link broken remark:{} isRemove:{}", remark, isRemove, throwable);
  }

}
