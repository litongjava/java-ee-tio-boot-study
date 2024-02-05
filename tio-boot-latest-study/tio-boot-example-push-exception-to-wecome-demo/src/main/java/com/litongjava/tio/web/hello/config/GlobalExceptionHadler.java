package com.litongjava.tio.web.hello.config;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.Map;

import com.litongjava.tio.boot.constatns.ConfigKeys;
import com.litongjava.tio.boot.exception.TioBootExceptionHandler;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.utils.environment.EnvironmentUtils;
import com.litongjava.tio.utils.network.IpUtils;
import com.litongjava.tio.utils.notification.NotifactionWarmModel;
import com.litongjava.tio.utils.notification.NotificationUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GlobalExceptionHadler implements TioBootExceptionHandler {

  @Override
  public void handler(HttpRequest request, Throwable e) {
    StringBuffer requestURL = request.getRequestURL();
    Map<String, String> headers = request.getHeaders();
    String bodyString = request.getBodyString();

    // 获取完整的堆栈跟踪
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    e.printStackTrace(pw);
    String stackTrace = sw.toString();

    log.info("{},{},{},{}", requestURL.toString(), headers, bodyString, stackTrace);
    NotifactionWarmModel model = new NotifactionWarmModel();

    String localIp = IpUtils.getLocalIp();
    model.setAppGroupName("tio-boot");
    model.setAppName(EnvironmentUtils.get(ConfigKeys.APP_NAME));
    model.setWarningName("运行异常");
    model.setLevel("普通级别");

    model.setDeviceName(localIp);
    model.setTime(new Date());
    model.setContent(requestURL + "\n" + stackTrace);

    NotificationUtils.sendWarm(model);

  }
}