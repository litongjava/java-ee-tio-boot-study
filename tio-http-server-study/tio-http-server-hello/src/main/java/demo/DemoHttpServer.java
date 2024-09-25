package demo;

import java.io.IOException;

import com.litongjava.model.body.RespBodyVo;
import com.litongjava.tio.http.common.HttpConfig;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.common.handler.ITioHttpRequestHandler;
import com.litongjava.tio.http.server.HttpServerStarter;
import com.litongjava.tio.http.server.handler.DefaultHttpRequestDispatcher;
import com.litongjava.tio.http.server.router.DefaultHttpReqeustRouter;
import com.litongjava.tio.http.server.router.HttpRequestRouter;
import com.litongjava.tio.server.ServerTioConfig;

public class DemoHttpServer {

  public static void main(String[] args) throws IOException {
    // 手动添加路由
    HttpRequestRouter simpleHttpRoutes = new DefaultHttpReqeustRouter();

    simpleHttpRoutes.add("/ok", (request) -> {
      return new HttpResponse(request).setJson(RespBodyVo.ok("ok"));
    });
    //httpConfig
    HttpConfig httpConfig = new HttpConfig(80, null, null, null);
    httpConfig.setUseSession(false);
    httpConfig.setWelcomeFile(null);
    httpConfig.setCheckHost(false);

    //requestHandler
    ITioHttpRequestHandler requestHandler = new DefaultHttpRequestDispatcher(httpConfig, simpleHttpRoutes);
    //httpServerStarter
    HttpServerStarter httpServerStarter = new HttpServerStarter(httpConfig, requestHandler);
    ServerTioConfig serverTioConfig = httpServerStarter.getServerTioConfig();
    serverTioConfig.setServerAioListener(null);
    serverTioConfig.statOn = false;
    serverTioConfig.setHeartbeatTimeout(0);
    //serverTioConfig.setUseQueueSend(false);

    httpServerStarter.start();
  }

}