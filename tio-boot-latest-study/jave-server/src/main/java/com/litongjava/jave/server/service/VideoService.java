package com.litongjava.jave.server.service;

import java.io.File;
import java.io.IOException;

import ws.schild.jave.process.ffmpeg.DefaultFFMPEGLocator;

public class VideoService {

  public void frames(File outputFolder, File source) {
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
          outputFolder.getAbsolutePath() + "/frame_%d.jpg" };

      // Execute the command
      ProcessBuilder processBuilder = new ProcessBuilder(command);
      Process process = processBuilder.start();
      process.waitFor(); // Wait for the process to complete
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }

}
