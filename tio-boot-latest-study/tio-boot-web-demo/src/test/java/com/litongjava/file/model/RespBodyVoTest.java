package com.litongjava.file.model;

import com.litongjava.model.body.RespBodyVo;
import com.litongjava.tio.utils.json.JsonUtils;

public class RespBodyVoTest {

  
  public static void main(String[] args) {
    RespBodyVo respBodyVo = RespBodyVo.ok();
    String json = JsonUtils.toJson(respBodyVo);
    System.out.println(json);
  }
}
