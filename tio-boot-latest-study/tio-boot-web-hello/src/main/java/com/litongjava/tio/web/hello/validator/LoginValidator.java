package com.litongjava.tio.web.hello.validator;

import com.jfinal.kit.StrKit;
import com.litongjava.tio.utils.resp.RespVo;

public class LoginValidator {
  public RespVo validateLogin(String username, String password, String verificationCode) {
    RespVo retval = null;

    //验证username
    if (StrKit.isBlank(username)) {
      retval = RespVo.fail("The username or password cannot be empty");
      return retval;
    }

    //验证其他字段
    // ...
    //验证成功返回null,验证不成功返回RespVo
    return retval;
  }
}
