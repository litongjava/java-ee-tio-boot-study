package com.litongjava.jave.server.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.litongjava.jave.server.service.MediaService;
import com.litongjava.jave.server.service.VideoService;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.tio.boot.http.TioRequestContext;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.common.UploadFile;
import com.litongjava.tio.http.server.annotation.EnableCORS;
import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.http.server.util.Resps;
import com.litongjava.tio.utils.hutool.FileUtil;
import com.litongjava.tio.utils.resp.RespVo;

import lombok.extern.slf4j.Slf4j;

@RequestPath("/api/v1/media")
@EnableCORS
@Slf4j
public class ApiV1MediaController {
  private MediaService mediaService = Aop.get(MediaService.class);

  public HttpResponse toMp3(UploadFile file) {
    HttpResponse response = TioRequestContext.getResponse();

    if (file == null) {
      response.setStatus(400);
      response.setJson(RespVo.fail("upload file is empty"));
      return response;
    }

    String inputFolderName = "input";
    String outputFolderName = "output";

    byte[] fileData = file.getData();
    File inputFolder = new File(inputFolderName);
    if (!inputFolder.exists()) {
      inputFolder.mkdirs();
    }
    File outputFolder = new File(outputFolderName);

    if (!outputFolder.exists()) {
      outputFolder.mkdirs();
    }

    File source = new File(inputFolderName + "/" + file.getName());

    FileUtil.writeBytes(fileData, source);

    File target = new File(outputFolder + "/audio.mp3");

    mediaService.toMp3(source, target);

    byte[] bytes = FileUtil.readBytes(target);
    return Resps.bytesWithContentType(response, bytes, "audio/mp3");

  }

  public HttpResponse frames(UploadFile file) {
    HttpResponse response = TioRequestContext.getResponse();
    String pageRoot = TioBootServer.me().getHttpConfig().getPageRoot();

    if (file == null) {
      response.setStatus(400);
      response.setJson(RespVo.fail("upload file is empty"));
      return response;
    }

    String inputFolderName = "input";
    String outputFolderName = pageRoot + File.separator + "output";

    byte[] fileData = file.getData();
    File inputFolder = new File(inputFolderName);
    if (!inputFolder.exists()) {
      inputFolder.mkdirs();
    }
    File outputFolder = new File(outputFolderName);

    if (!outputFolder.exists()) {
      outputFolder.mkdirs();
    }

    File source = new File(inputFolderName + "/" + file.getName());

    FileUtil.writeBytes(fileData, source);

    VideoService videoService = Aop.get(VideoService.class);
    videoService.frames(outputFolder, source);
    log.info("outputFolder:{}", outputFolder.getAbsolutePath());

    String[] filenames = outputFolder.list();
    List<String> filePaths = new ArrayList<>(filenames.length);
    for (String string : filenames) {
      filePaths.add("/output/" + string);
    }

    return response.setJson(RespVo.ok(filePaths));

  }
}
