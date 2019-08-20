package com.macro.mall.controller;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.SysMenu;
import com.macro.mall.model.UmsMemberLevel;
import com.macro.mall.service.SysMenuService;
import com.macro.mall.service.UmsMemberLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * 系统菜单管理Controller
 *
 * @author land
 */
@RestController
@Api(tags = "SysMenuController", description = "系统菜单管理")
@RequestMapping("/sys/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation("查询所有菜单")
    public CommonResult<List<SysMenu>> list() {
        List<SysMenu> sysMenuList = sysMenuService.list();
        return CommonResult.success(sysMenuList);
    }
}
