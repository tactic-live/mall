package com.macro.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * 更改退款原因状态参数
 */
@Getter
@Setter
public class OmsUpdateReturnReasonStatusParam {
    @ApiModelProperty("状态: 0->不开启 1->已开启")
    private Integer status;
    @ApiModelProperty("退款原因id")
    private List<Long> ids;

    public List getIds() { return ids; }
    public Integer getStatus() { return status; }
}
