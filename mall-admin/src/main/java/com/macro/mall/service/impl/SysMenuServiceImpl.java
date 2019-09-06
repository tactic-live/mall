package com.macro.mall.service.impl;

import com.macro.mall.mapper.SysMenuMapper;
import com.macro.mall.model.SysMenu;
import com.macro.mall.model.SysMenuExample;
import com.macro.mall.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public int create(SysMenu sysMenu) {
        sysMenu.setUpdateTime(new Date());
        return sysMenuMapper.insert(sysMenu);
    }

    @Override
    public int update(Long id, SysMenu sysMenu) {
        sysMenu.setId(id);
        sysMenu.setUpdateTime(new Date());
        return sysMenuMapper.updateByPrimaryKey(sysMenu);
    }

    @Override
    public int delete(Long id) {
        return sysMenuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SysMenu selectByPrimaryKey(Long id) {
        return sysMenuMapper.selectByPrimaryKey(id);
    }
}
