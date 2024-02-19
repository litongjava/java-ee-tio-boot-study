package com.litongjava.tio.boo.demo.file.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.litongjava.jfinal.aop.annotation.AAutowired;
import com.litongjava.tio.boo.demo.file.model.FileSaveResult;
import com.litongjava.tio.boo.demo.file.services.FileService;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.common.UploadFile;
import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.http.server.util.Resps;
import com.litongjava.tio.utils.resp.RespVo;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

@RequestPath("/file")
@Slf4j
public class FileController {

  @AAutowired
  private FileService fileService;

  public String index() {
    return "index";
  }

  /**
   * upload file
   * @param file
   * @return
   */
  public RespVo upload(UploadFile file) {
    if (file != null) {
      String name = file.getName();
      byte[] fileData = file.getData();
      // save file
      FileSaveResult fileSaveResult = fileService.save(name, fileData);
      fileSaveResult.setFile(null);

      log.info("save file finished");
      return RespVo.ok(fileSaveResult);

    }
    return RespVo.fail("fail").code(-1);
  }

  @RequestPath("/download/{id}")
  public HttpResponse download(HttpRequest request, String id) {
    log.info("id:{}", id);
    File file = fileService.getUploadFile(id);
    int available;
    try {
      @Cleanup
      InputStream inputStream = new FileInputStream(file);
      available = inputStream.available();
      byte[] fileBytes = new byte[available];
      inputStream.read(fileBytes, 0, available);
      String contentType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document; charset=utf-8";
      HttpResponse response = Resps.bytesWithContentType(request, fileBytes, contentType);
      return response;
    } catch (IOException e) {
      e.printStackTrace();
      return Resps.json(request, RespVo.fail("Error generating captcha"));
    }
  }
}