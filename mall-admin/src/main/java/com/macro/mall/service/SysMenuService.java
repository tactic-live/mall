package com.macro.mall.service;

import com.macro.mall.model.SysMenu;

import java.util.List;

/**
 * 系统菜单管理Service
 *
 * @author land
 */
public interface SysMenuService extends CommOperate<SysMenu> {

    /**
     * 获取所有菜单信息
     */
    List<SysMenu> list();

}
