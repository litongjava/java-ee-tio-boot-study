package com.litongjava.jave.server.service;

import java.io.File;

import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.InputFormatException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;

public class MediaService {

  public void toMp3(File source, File target) {
    // 设置音频属性
    AudioAttributes audio = new AudioAttributes();
    audio.setCodec("libmp3lame");
    audio.setBitRate(128000);
    audio.setChannels(2);
    audio.setSamplingRate(44100);

    // 设置编码属性
    EncodingAttributes attrs = new EncodingAttributes();
    attrs.setInputFormat("mp4");
    attrs.setOutputFormat("mp3");
    attrs.setAudioAttributes(audio);

    // 开始编码
    Encoder encoder = new Encoder();
    try {
      encoder.encode(new MultimediaObject(source), target, attrs);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (InputFormatException e) {
      e.printStackTrace();
    } catch (EncoderException e) {
      e.printStackTrace();
    }
  }

}
