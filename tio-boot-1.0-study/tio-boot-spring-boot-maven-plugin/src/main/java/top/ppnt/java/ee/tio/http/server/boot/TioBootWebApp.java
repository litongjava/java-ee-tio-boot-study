package top.ppnt.java.ee.tio.http.server.boot;

import org.tio.utils.jfinal.P;

import com.litongjava.tio.http.server.boot.TioHttpServerApplication;

public class TioBootWebApp {

  public static void main(String[] args) throws Exception {
    long start = System.currentTimeMillis();
    P.use("app.properties");
    TioHttpServerApplication.run(TioBootWebApp.class, args);
    long end = System.currentTimeMillis();

    System.out.println("started:" + (end - start) + "(ms)");
  }
}