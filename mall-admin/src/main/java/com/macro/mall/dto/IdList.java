package com.macro.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * id列表
 */
@Data
public class IdList {
	@ApiModelProperty("编号列表")
	private List<Long> ids;
}
