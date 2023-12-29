package demo.mybatis.plus;

import com.litongjava.jfinal.aop.annotation.ComponentScan;
import com.litongjava.tio.boot.TioApplication;

@ComponentScan
public class MybatisApp {
  public static void main(String[] args) {
    TioApplication.run(MybatisApp.class, args);
  }

}
