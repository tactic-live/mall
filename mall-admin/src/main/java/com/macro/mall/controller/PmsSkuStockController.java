package com.macro.mall.controller;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.PmsSkuStock;
import com.macro.mall.service.PmsSkuStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * sku库存Controller
 * Created by macro on 2018/4/27.
 */
@Controller
@Api(tags = "PmsSkuStockController", description = "sku商品库存管理")
@RequestMapping("/sku")
public class PmsSkuStockController {
    @Autowired
    private PmsSkuStockService skuStockService;

    @ApiOperation("根据商品编号及编号模糊搜索sku库存")
    @RequestMapping(value = "/{pid}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsSkuStock>> getList(@PathVariable Long pid, @RequestParam(value = "keyword", required = false) String keyword) {
        List<PmsSkuStock> skuStockList = skuStockService.getList(pid, keyword);
        return CommonResult.success(skuStockList);
    }

    @ApiOperation("根据商品编号及编号模糊搜索sku信息包含库存")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsSkuStock>> getSkuListByProductId(@RequestParam(required = false) String pid) {
        if (StringUtils.isNumeric(pid)) {
            List<PmsSkuStock> skuStockList = skuStockService.getSkuListByProductId(Long.parseLong(pid));
            return CommonResult.success(skuStockList);
        }
        return CommonResult.success(new ArrayList<>());
    }

    @ApiOperation("批量更新库存信息")
    @RequestMapping(value = "/update/{pid}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long pid, @RequestBody List<PmsSkuStock> skuStockList) {
        int count = skuStockService.update(pid, skuStockList);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("批量更新库存信息")
    @RequestMapping(value = "/batch/{pid}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateSkuStocksByProductId(@PathVariable Long pid, @RequestBody List<PmsSkuStock> skuStockList) {
        return update(pid, skuStockList);
    }

//    @ApiOperation("批量部分更新库存信息")
//    @RequestMapping(value = "/batch/{pid}", method = RequestMethod.PATCH)
//    @ResponseBody
//    public CommonResult patchSkuStocksByProductId(@PathVariable Long pid, @RequestBody List<PmsSkuStock> skuStockList) {
//        return update(pid, skuStockList);
//    }
}
