package com.macro.mall.dto;

import com.macro.mall.validator.FlagValidator;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * 商品属性参数
 * Created by macro on 2018/4/26.
 */
@Data
public class PmsProductAttributeCategoryParam {
    @ApiModelProperty("属性分类ID")
    private String id;
    @ApiModelProperty("属性名称")
    @NotEmpty(message = "属性名称不能为空")
    private String name;

}
