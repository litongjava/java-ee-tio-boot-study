package com.litongjava.tio.boot.hello.tcp;

import com.litongjava.jfinal.aop.annotation.Component;
import com.litongjava.tio.boot.tcp.ServerListener;
import com.litongjava.tio.core.ChannelContext;
import com.litongjava.tio.core.intf.Packet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DemoListener implements ServerListener {
  public void onAfterConnected(ChannelContext channelContext, boolean isConnected, boolean isReconnect)
      throws Exception {
    log.info("{},{},{}", channelContext, isConnected, isReconnect);
  }

  public void onAfterDecoded(ChannelContext channelContext, Packet packet, int packetSize) throws Exception {
    log.info("{},{},{}", channelContext, packet, packetSize);
  }

  public void onAfterReceivedBytes(ChannelContext channelContext, int receivedBytes) throws Exception {
    log.info("{},{}", channelContext, receivedBytes);
  }

  public void onAfterSent(ChannelContext channelContext, Packet packet, boolean isSentSuccess) throws Exception {
    log.info("{},{}", channelContext, packet);
  }

  public void onAfterHandled(ChannelContext channelContext, Packet packet, long cost) throws Exception {
    log.info("{},{},{}", channelContext, packet, cost);
  }

  /**
   * 连接关闭前触发本方法
   *
   * @param channelContext        the channelcontext
   * @param throwable the throwable 有可能为空
   * @param remark    the remark 有可能为空
   * @param isRemove
   * @throws Exception
   */

  public void onBeforeClose(ChannelContext channelContext, Throwable throwable, String remark, boolean isRemove)
      throws Exception {
    log.info("{},{},{},{}", channelContext, throwable, remark, isRemove);
  }

  /**
   * @param channelContext
   * @param interval              已经多久没有收发消息了，单位：毫秒
   * @param heartbeatTimeoutCount 心跳超时次数，第一次超时此值是1，以此类推。此值被保存在：channelContext.stat.heartbeatTimeoutCount
   * @return 返回true，那么服务器则不关闭此连接；返回false，服务器将按心跳超时关闭该连接
   */
  public boolean onHeartbeatTimeout(ChannelContext channelContext, Long interval, int heartbeatTimeoutCount) {
    log.info("{},{},{}", channelContext, interval, heartbeatTimeoutCount);
    return false;
  }
}
