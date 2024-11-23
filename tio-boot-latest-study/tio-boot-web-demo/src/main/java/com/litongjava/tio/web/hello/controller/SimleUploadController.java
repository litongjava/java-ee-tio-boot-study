package com.litongjava.tio.web.hello.controller;

import java.io.File;

import com.litongjava.annotation.RequestPath;
import com.litongjava.tio.boot.http.TioRequestContext;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.common.UploadFile;
import com.litongjava.tio.utils.hutool.FileUtil;

@RequestPath(value = "/simple/upload")
public class SimleUploadController {

  @RequestPath(value = "")
  public HttpResponse index(UploadFile uploadFile){
    if (uploadFile != null) {
      byte[] fileData = uploadFile.getData();
      File file = new File(uploadFile.getName());
      FileUtil.writeBytes(fileData, file);
    }
    HttpResponse response = TioRequestContext.getResponse();
    return response.setString("sucess");
  }

}
