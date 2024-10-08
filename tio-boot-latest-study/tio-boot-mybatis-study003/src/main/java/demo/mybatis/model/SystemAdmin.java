package demo.mybatis.model;

import lombok.Data;

@Data
public class SystemAdmin {
  private int id;
  private String loginName;
  private String password;
}