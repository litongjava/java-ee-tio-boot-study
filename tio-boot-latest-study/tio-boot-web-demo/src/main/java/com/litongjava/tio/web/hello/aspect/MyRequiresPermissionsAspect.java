package com.litongjava.tio.web.hello.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;

import com.litongjava.annotation.RequiresPermissions;
import com.litongjava.tio.boot.aspect.IRequiresPermissionsAspect;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyRequiresPermissionsAspect implements IRequiresPermissionsAspect {

  @Override
  public HttpResponse check(HttpRequest request, Object targetController, Method actionMethod, RequiresPermissions annotation) {
    log.info("annotation:{}", Arrays.toString(annotation.value()));
    return null;
  }

}
