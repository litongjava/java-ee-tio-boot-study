package demo.mybatis;

import com.litongjava.annotation.AComponentScan;
import com.litongjava.tio.boot.TioApplication;

@AComponentScan
public class MybatisApp {
  public static void main(String[] args) {
    TioApplication.run(MybatisApp.class, args);
  }
}
