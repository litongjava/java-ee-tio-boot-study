package com.litongjava.tio.web.hello;

import com.litongjava.jfinal.aop.annotation.AConfiguration;
import com.litongjava.jfinal.aop.annotation.AInitialization;
import com.litongjava.jfinal.plugin.satoken.SaTokenDaoRedis;
import com.litongjava.tio.boot.satoken.SaTokenContextForTio;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.config.SaCookieConfig;
import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.context.SaTokenContext;
import cn.dev33.satoken.util.SaTokenConsts;

@AConfiguration
public class SaTokenConfiguration {

  @AInitialization
  public void config() {
    SaTokenContext saTokenContext = new SaTokenContextForTio();

    SaCookieConfig saCookieConfig = new SaCookieConfig();
    // 开启cookies的httponly属性
    saCookieConfig.setHttpOnly(true);

    // 加载权限角色设置数据接口
    // SaManager.setStpInterface(new StpInterfaceImpl());
    SaTokenConfig saTokenConfig = new SaTokenConfig();
    // 设置token生成类型
    saTokenConfig.setTokenStyle(SaTokenConsts.TOKEN_STYLE_SIMPLE_UUID);
    // 50分钟无操作过期处理 登录有效时间4小时
    saTokenConfig.setActiveTimeout(50 * 60);

    saTokenConfig.setIsShare(false);
    // 更改satoken的cookies名称
    saTokenConfig.setTokenName("token");

    //将token写入请求求
    saTokenConfig.setIsWriteHeader(true);
    //从请求头中读取token
    saTokenConfig.setIsReadHeader(true);

    saTokenConfig.setCookie(saCookieConfig);
    SaManager.setConfig(saTokenConfig);
    SaManager.setSaTokenContext(saTokenContext);
    SaManager.setSaTokenDao(new SaTokenDaoRedis("main"));
  }
}
