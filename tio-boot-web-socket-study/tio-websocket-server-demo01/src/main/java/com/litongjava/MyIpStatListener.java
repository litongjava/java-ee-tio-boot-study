package com.litongjava;

import com.litongjava.aio.Packet;
import com.litongjava.tio.core.ChannelContext;
import com.litongjava.tio.core.TioConfig;
import com.litongjava.tio.core.stat.IpStat;
import com.litongjava.tio.core.stat.IpStatListener;

public class MyIpStatListener implements IpStatListener {

  public void onExpired(TioConfig tioConfig, IpStat ipStat) {
  }

  public void onAfterConnected(ChannelContext channelContext, boolean isConnected, boolean isReconnect, IpStat ipStat) throws Exception {
  }

  public void onDecodeError(ChannelContext channelContext, IpStat ipStat) {
  }

  public void onAfterSent(ChannelContext channelContext, Packet packet, boolean isSentSuccess, IpStat ipStat) throws Exception {
  }

  public void onAfterDecoded(ChannelContext channelContext, Packet packet, int packetSize, IpStat ipStat) throws Exception {
  }

  public void onAfterReceivedBytes(ChannelContext channelContext, int receivedBytes, IpStat ipStat) throws Exception {
  }

  public void onAfterHandled(ChannelContext channelContext, Packet packet, IpStat ipStat, long cost) throws Exception {
  }

}