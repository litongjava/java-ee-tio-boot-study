package top.ppnt.java.ee.tio.http.server.boot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
  private Integer id;
  private String loginName;
  private String nick;
  private String ip;
}
