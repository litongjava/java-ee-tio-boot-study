package demo.mybatis.plus.model;
import lombok.Data;

@Data
public class User {
  private Long id;
  private String name;
  private int age;
  private String email;
  private String addr;
  private String remark;
}