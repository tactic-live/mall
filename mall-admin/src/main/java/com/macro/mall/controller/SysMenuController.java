package com.macro.mall.controller;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.SysMenu;
import com.macro.mall.model.UmsAdmin;
import com.macro.mall.model.UmsMemberLevel;
import com.macro.mall.service.SysMenuService;
import com.macro.mall.service.UmsAdminService;
import com.macro.mall.service.UmsMemberLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Autowired
    private UmsAdminService adminService;

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation("查询所有菜单")
    public CommonResult<List<SysMenu>> list() {
        List<SysMenu> sysMenuList = sysMenuService.list();
        return CommonResult.success(sysMenuList);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation("修改菜单")
    public CommonResult<Integer> modifySysMenu(@RequestBody SysMenu sysMenu) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UmsAdmin userInfo = adminService.getAdminByUsername(username);
        sysMenu.setUpdateUser(userInfo.getId());
        int count = sysMenuService.update(sysMenu.getId(), sysMenu);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation("增加菜单")
    public CommonResult<SysMenu> addSysMenu(@RequestBody SysMenu sysMenu) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UmsAdmin userInfo = adminService.getAdminByUsername(username);
        sysMenu.setUpdateUser(userInfo.getId());
        int count = sysMenuService.create(sysMenu);
        if (count > 0) {
            return CommonResult.success(sysMenu);
        }
        return CommonResult.failed();
    }
}
