package demo.mybatis;

import com.litongjava.annotation.AComponentScan;
import com.litongjava.hotswap.wrapper.tio.boot.TioApplicationWrapper;

@AComponentScan
public class MybatisApp {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    TioApplicationWrapper.run(MybatisApp.class, args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "(ms)");
  }
}
