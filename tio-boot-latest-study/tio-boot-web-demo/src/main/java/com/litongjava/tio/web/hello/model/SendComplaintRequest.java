package com.litongjava.tio.web.hello.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "投诉意见入参")
public class SendComplaintRequest {
  @ApiModelProperty(value = "0=投诉用户 1=平台功能")
  public String complaintsType;
  @ApiModelProperty(value = "内容描述")
  public String complaintsNote;
  @ApiModelProperty(value = "图片视频")
  public List<String> complaintsImg;
  @ApiModelProperty(value = "联系方式")
  public String complaintsContact;
}
