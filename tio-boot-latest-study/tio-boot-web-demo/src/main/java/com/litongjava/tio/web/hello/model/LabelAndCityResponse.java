package com.litongjava.tio.web.hello.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "城市和分类信息出参")
public class LabelAndCityResponse {

  @ApiModelProperty(value = "城市信息")
  public CityBean city;
  @ApiModelProperty(value = "首页分类信息")
  public List<LabelListBean> labelList;

  public static class CityBean {
    /**
     * cityName : 成都市
     * cityProvince : 四川省
     * cityId : 1
     */
    @ApiModelProperty(value = "城市名称")
    public String cityName;
    @ApiModelProperty(value = "归属省份")
    public String cityProvince;
    @ApiModelProperty(value = "城市ID")
    public int cityId;
  }

  public static class LabelListBean {
    @ApiModelProperty(value = "分类ID")
    public int classId;
    @ApiModelProperty(value = "路由URL")
    public String classUrl;
    @ApiModelProperty(value = "分类名称")
    public String className;

  }
}
