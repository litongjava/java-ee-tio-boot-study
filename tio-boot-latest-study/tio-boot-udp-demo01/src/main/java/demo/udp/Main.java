package demo.udp;

import com.litongjava.hotswap.wrapper.tio.boot.TioApplicationWrapper;
import com.litongjava.jfinal.aop.annotation.AComponent;
import com.litongjava.jfinal.aop.annotation.AComponentScan;

@AComponentScan
public class Main {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    TioApplicationWrapper.run(Main.class, args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "(ms)");
  }
}
