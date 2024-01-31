package demo;

import java.io.IOException;

import com.litongjava.tio.http.common.HttpConfig;
import com.litongjava.tio.http.common.handler.HttpRequestHandler;
import com.litongjava.tio.http.server.HttpServerStarter;
import com.litongjava.tio.http.server.handler.HttpRoutes;
import com.litongjava.tio.http.server.handler.SimpleHttpDispatcherHandler;
import com.litongjava.tio.http.server.handler.SimpleHttpRoutes;
import com.litongjava.tio.utils.json.JFinalJsonKit;
import demo.controller.IndexController;

public class DemoHttpServer {

  public static void main(String[] args) throws IOException {

    JFinalJsonKit.setTreatModelAsBean(true);

    // 实例化Controller
    IndexController controller = new IndexController();

    // 手动添加路由
    HttpRoutes simpleHttpRoutes = new SimpleHttpRoutes();
    simpleHttpRoutes.add("/", controller::index);
    simpleHttpRoutes.add("/txt", controller::txt);
    simpleHttpRoutes.add("/json", controller::json);
    simpleHttpRoutes.add("/exception", controller::exception);

    // 配置服务服务器
    HttpConfig httpConfig;
    HttpRequestHandler requestHandler;
    HttpServerStarter httpServerStarter;

    httpConfig = new HttpConfig(80, null, null, null);
    requestHandler = new SimpleHttpDispatcherHandler(httpConfig, simpleHttpRoutes);
    httpServerStarter = new HttpServerStarter(httpConfig, requestHandler);
    // 启动服务器
    httpServerStarter.start();
  }

}