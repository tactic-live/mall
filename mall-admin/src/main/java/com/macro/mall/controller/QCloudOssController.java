package com.macro.mall.controller;


import com.macro.mall.common.api.CommonResult;
import com.macro.mall.dto.OssCallbackResult;
import com.macro.mall.dto.OssPolicyResult;
import com.macro.mall.service.QCloudOssService;
import com.macro.mall.service.impl.OssServiceImpl;
import com.macro.mall.service.impl.QCloudOssServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Oss相关操作接口
 * Created by landfei on 2019/7/2.
 */
@Controller
@Api(tags = "QCloudOssController", description = "QCloudOss管理")
@RequestMapping("/qcloud/oss")
public class QCloudOssController {
    @Autowired
    private QCloudOssService qCloudOssService;

    @ApiOperation(value = "oss上传签名生成")
    @RequestMapping(value = "/policy", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Map> policy() {
        JSONObject result = qCloudOssService.policy();
        return CommonResult.success(result.toMap());
    }
}
