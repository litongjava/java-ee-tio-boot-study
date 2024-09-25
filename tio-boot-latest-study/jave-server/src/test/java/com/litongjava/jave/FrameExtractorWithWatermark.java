package com.litongjava.jave;

import java.io.File;
import java.io.IOException;

import ws.schild.jave.process.ffmpeg.DefaultFFMPEGLocator;

public class FrameExtractorWithWatermark {

  public static void main(String[] args) {
    String filePath = "video.mp4";
    File source = new File(filePath);

    File outputDir = new File("output/");
    outputDir.mkdirs();

    // 创建一个locator对象
    DefaultFFMPEGLocator locator = new DefaultFFMPEGLocator();

    try {
      // Command to extract frames with frame number watermark using FFmpeg
      String[] command = { locator.getExecutablePath(), "-i",
          //
          source.getAbsolutePath(),
          //
          "-vf", "fps=1,drawtext=text='%{n}':x=10:y=H-th-10:fontsize=50:fontcolor=red",
          //
          outputDir.getAbsolutePath() + "/frame%d.jpg" };

      // Execute the command
      ProcessBuilder processBuilder = new ProcessBuilder(command);
      Process process = processBuilder.start();
      process.waitFor(); // Wait for the process to complete

      System.out.println("Frames extracted successfully with watermark.");
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("finish");
  }
}
