package com.macro.mall.controller;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.dto.IdList;
import com.macro.mall.dto.SmsRecommendStatusParam;
import com.macro.mall.dto.SmsSortParam;
import com.macro.mall.model.SmsHomeBrand;
import com.macro.mall.service.SmsHomeBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 首页品牌管理Controller
 * Created by macro on 2018/11/6.
 */
@Controller
@Api(tags = "SmsHomeBrandController", description = "首页品牌管理")
@RequestMapping("/home/brand")
public class SmsHomeBrandController {
    @Autowired
    private SmsHomeBrandService homeBrandService;

//    @ApiOperation("添加首页推荐品牌")
//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult create(@RequestBody List<SmsHomeBrand> homeBrandList) {
//        int count = homeBrandService.create(homeBrandList);
//        if (count > 0) {
//            return CommonResult.success(count);
//        }
//        return CommonResult.failed();
//    }

    @ApiOperation("修改后-添加首页推荐品牌")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody List<SmsHomeBrand> homeBrandList) {
        List<SmsHomeBrand> result = homeBrandService.create(homeBrandList);
        if (result.size() > 0) {
            return CommonResult.success(result);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改品牌排序")
    @RequestMapping(value = "/update/sort/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateSort(@PathVariable Long id, Integer sort) {
        int count = homeBrandService.updateSort(id, sort);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改后-修改品牌排序")
    @RequestMapping(value = "/sort", method = RequestMethod.PUT)
    @ResponseBody
    public CommonResult updateSort(@RequestBody SmsSortParam smsSortParam) {
        int count = homeBrandService.updateSort(smsSortParam.getId(), smsSortParam.getSort());
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("批量删除推荐品牌")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = homeBrandService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改后-批量删除推荐品牌")
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult delete(@RequestBody IdList idList) {
        int count = homeBrandService.delete(idList.getIds());
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("批量修改推荐状态")
    @RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        int count = homeBrandService.updateRecommendStatus(ids, recommendStatus);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改后-批量修改推荐状态")
    @RequestMapping(value = "/recommendStatus", method = RequestMethod.PATCH)
    @ResponseBody
    public CommonResult updateRecommendStatus(@RequestBody SmsRecommendStatusParam smsRecommendStatusParam) {
        int count = homeBrandService.updateRecommendStatus(smsRecommendStatusParam.getIds(), smsRecommendStatusParam.getRecommendStatus());
//        if (smsRecommendStatusParam.getRecommendStatus() == 0) {
//            // 如果是取消推荐状态 把排序值设为0
//            updateSort()
//        }
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("分页查询推荐品牌")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<SmsHomeBrand>> list(@RequestParam(value = "brandName", required = false) String brandName,
                                                       @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsHomeBrand> homeBrandList = homeBrandService.list(brandName, recommendStatus, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(homeBrandList));
    }
}
