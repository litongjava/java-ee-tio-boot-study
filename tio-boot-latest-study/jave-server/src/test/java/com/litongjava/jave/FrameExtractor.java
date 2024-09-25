package com.litongjava.jave;

import java.io.File;
import java.io.IOException;

import ws.schild.jave.process.ffmpeg.DefaultFFMPEGLocator;

public class FrameExtractor {

  public static void main(String[] args) {
    File source = new File("G:\\video\\03.软件开发学习视频\\嵌入式\\亚博智能 4WD智能小车（树莓派）\\1.入手组装\\0.亚博智能树莓派-4WD智能小车 扩展板介绍.mp4");

    File outputDir = new File("output/");
    outputDir.mkdirs();

    // 创建一个locator对象
    DefaultFFMPEGLocator locator = new DefaultFFMPEGLocator();

    try {
      // Command to extract frames using FFmpeg
      String[] command = { locator.getExecutablePath(), "-i",
          // source
          source.getAbsolutePath(), "-vf", "fps=1",
          // output
          outputDir.getAbsolutePath() + "/frame%d.jpg" };

      // Execute the command
      ProcessBuilder processBuilder = new ProcessBuilder(command);
      Process process = processBuilder.start();
      process.waitFor(); // Wait for the process to complete

      System.out.println("Frames extracted successfully.");
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("finish");

  }
}
