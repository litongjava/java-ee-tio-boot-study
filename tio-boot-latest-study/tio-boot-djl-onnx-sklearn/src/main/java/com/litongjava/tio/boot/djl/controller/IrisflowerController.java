package com.litongjava.tio.boot.djl.controller;

import org.tio.http.server.annotation.RequestPath;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.tio.boot.djl.services.IrisflowerService;

import ai.djl.modality.Classifications;

@RequestPath("/Irisflower")
public class IrisflowerController {

  @RequestPath("")
  public String index() {
    Classifications result = Aop.get(IrisflowerService.class).index();
    return result.toJson();
  }
}
