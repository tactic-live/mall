package com.macro.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 修改推荐状态参数
 */
@Data
public class SmsRecommendStatusParam extends IdList {
    @ApiModelProperty("状态: 0->不开启 1->已开启")
    private Integer recommendStatus;
}
