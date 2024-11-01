package com.litongjava.tio.web.hello.controller;

import com.litongjava.annotation.GatewayCheck;
import com.litongjava.annotation.RequestPath;
import com.litongjava.model.body.RespBodyVo;
import com.litongjava.model.result.ResultVo;
import com.litongjava.tio.web.hello.model.LabelAndCityResponse;
import com.litongjava.tio.web.hello.model.SendComplaintRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "域名相关API")
@RequestPath("/app/domain")
public class AppDomainController {

  /**
   * 获取指定域名ID的域名信息
   *
   * @return
   */
  // paramType "query", "path", "header"
  @GatewayCheck(checkAdminToken = false, checkUserToken = false)
  @RequestPath("/selectLrbAppDomainById")
  @ApiOperation(value = "获取指定域名ID的域名信息", notes = "请求方式GET,请求类型：application/x-www-form-urlencoded; charset=UTF-8")
  @ApiImplicitParams({
      //
      @ApiImplicitParam(value = "平台管理员登录凭证", name = "token", paramType = "header", required = true),
      //
      @ApiImplicitParam(value = "时间戳", name = "timestamp", paramType = "header", required = true),
      //
      @ApiImplicitParam(value = "管理员令牌(前端heAppDomainer携带管理员令牌，暂时通过接口文档里面的申请令牌接口获取！)",
          //
          name = "AppDomainministratorToken", paramType = "query", required = true),
      //
      @ApiImplicitParam(value = "域名ID", name = "AppDomainId", paramType = "query", required = true), })

  @ApiResponses({
      //
      @ApiResponse(code = 200, message = "请求成功"),
      //
      @ApiResponse(code = 0, message = "失败，会返回data，此参数为失败说明"),
      //
      @ApiResponse(code = 401, message = "无权限（通常是token不存在或无效）"),
      //
      @ApiResponse(code = 403, message = "网关校验失败！"),
      //
      @ApiResponse(code = 405, message = "管理员令牌校验失败（前端直接跳转一个友好的404页面）！"), })

  RespBodyVo selectLrbAppDomainById() {
    return RespBodyVo.ok();
  }

  RespBodyVo getById(Long id) {
    return RespBodyVo.ok();
  }

  @GatewayCheck(checkUserToken = false)
  @ApiOperation(value = "提交投诉申请", notes = "请求方式POST,请求类型：application/json; charset=UTF-8")
  @ApiImplicitParams({
      //
      @ApiImplicitParam(value = "登录凭证", name = "token", paramType = "header", required = true), })
  @ApiResponses({
      //
      @ApiResponse(code = 200, message = "请求成功"),
      //
      @ApiResponse(code = 0, message = "失败，会返回data，此参数为失败说明"),
      //
      @ApiResponse(code = 401, message = "无权限（通常是token不存在或无效）"),
      //
      @ApiResponse(code = 403, message = "网关校验失败！"), })
  ResultVo sendComplaint(SendComplaintRequest complaintRequest) {
    return ResultVo.ok();
  }

  @GatewayCheck(checkUserToken = false)
  @ApiOperation(value = "根据省市获取首页标签以及城市", notes = "请求方式GET,请求类型：application/x-www-form-urlencoded; charset=UTF-8")
  @ApiImplicitParams({
      //
      @ApiImplicitParam(value = "会员登录凭证", name = "token", paramType = "header", required = true),
      //
      @ApiImplicitParam(value = "省", name = "province", paramType = "header", required = true),
      //
      @ApiImplicitParam(value = "市", name = "city", paramType = "header", required = true), })
  @ApiResponses({
      //
      @ApiResponse(code = 200, message = "请求成功", response = LabelAndCityResponse.class),
      //
      @ApiResponse(code = 0, message = "失败，会返回data，此参数为失败说明"),
      //
      @ApiResponse(code = 401, message = "无权限（通常是token不存在或无效）"),
      //
      @ApiResponse(code = 403, message = "网关校验失败！"), })
  ResultVo getLabelAndCity() {
    return ResultVo.ok();
  }
}