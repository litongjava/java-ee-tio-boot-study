package com.litongjava.jave;

import ws.schild.jave.process.ffmpeg.DefaultFFMPEGLocator;

public class FfmpegPathExample {

  public static void main(String[] args) {
    // 创建一个locator对象
    DefaultFFMPEGLocator locator = new DefaultFFMPEGLocator();
    // 获取路径
    String executablePath = locator.getExecutablePath();
    // C:\Users\Administrator\AppData\Local\Temp\jave\ffmpeg-amd64-3.5.0.exe
    System.out.println(executablePath);
  }
}
