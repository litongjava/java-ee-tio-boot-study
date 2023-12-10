package com.litongjava.tio.boot.djl.controller;

import org.tio.http.server.annotation.RequestPath;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.jfinal.aop.Autowired;
import com.litongjava.tio.boot.djl.services.IrisflowerService;

import ai.djl.modality.Classifications;
import lombok.extern.slf4j.Slf4j;

@RequestPath("/Irisflower")
//@Controller
@Slf4j
public class IrisflowerController {

  @Autowired
//  @Inject
  IrisflowerService irisflowerService = Aop.get(IrisflowerService.class);

  @RequestPath("")
  public String index() {
    Classifications result = irisflowerService.index();
    return result.toJson();
  }

  @RequestPath("/irisflowerService")
  public String irisflowerService() {
    log.info("irisflowerService:{}", irisflowerService);
    return irisflowerService.toString();
  }
}
