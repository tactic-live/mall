package com.macro.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 更改人气推荐状态参数
 */
@Getter
@Setter
public class SmsHotRecommendStatusParam {
    @ApiModelProperty("状态: 0->不开启 1->已开启")
    private Integer recommendStatus;
    @ApiModelProperty("商品id")
    private List<Long> ids;

    public List getIds() { return ids; }
    public Integer getRecommendStatus() { return recommendStatus; }
}
