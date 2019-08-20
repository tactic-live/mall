package com.macro.mall.service.impl;

import com.macro.mall.dao.UmsRolePermissionRelationDao;
import com.macro.mall.mapper.SysMenuMapper;
import com.macro.mall.mapper.UmsRoleMapper;
import com.macro.mall.mapper.UmsRolePermissionRelationMapper;
import com.macro.mall.model.*;
import com.macro.mall.service.SysMenuService;
import com.macro.mall.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 系统菜单管理Service
 *
 * @author land
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> list() {
        SysMenuExample sysMenuExample = new SysMenuExample();
        sysMenuExample.setOrderByClause("parent_id, sort");
        return sysMenuMapper.selectByExample(sysMenuExample);
    }
}
