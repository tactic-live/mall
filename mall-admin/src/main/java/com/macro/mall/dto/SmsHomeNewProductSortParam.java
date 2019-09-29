package com.macro.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 修改新品推荐排序参数
 */
@Setter
@Getter
public class SmsHomeNewProductSortParam {
    @ApiModelProperty("编号")
    private Long id;
    @ApiModelProperty("排序值")
    private Integer sort;

    public Long getId(){return id;}
    public Integer getSort() {return sort;}
}
