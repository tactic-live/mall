package com.macro.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 修改广告上下线状态
 */
@Data
public class SmsAdvertiseStatusParam {
    @ApiModelProperty("广告id")
    private Long id;
    @ApiModelProperty("状态: 0->下线 1->上线")
    private Integer status;
}
