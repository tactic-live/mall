package com.macro.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 更改人气推荐商品排序参数
 */
@Getter
@Setter
public class SmsHotRecommendSortParam {
    @ApiModelProperty("商品id")
    private Long id;
    @ApiModelProperty("排序值")
    private Integer sort;

    public Long getId() { return id; }
    public Integer getSort() { return sort; }

}
