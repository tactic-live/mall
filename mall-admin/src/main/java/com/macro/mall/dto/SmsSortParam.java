package com.macro.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 修改新品推荐排序参数
 */
@Data
public class SmsSortParam {
    @ApiModelProperty("编号")
    private Long id;
    @ApiModelProperty("排序值")
    private Integer sort;
}
