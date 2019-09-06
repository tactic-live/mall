package com.macro.mall.service;

import org.springframework.transaction.annotation.Transactional;

public interface CommOperate<T> {

    /**
     * 添加信息
     */
    @Transactional
    int create(T t);


    /**
     * 修改指定信息
     */
    int update(Long id, T t);

    /**
     * 删除指定信息
     */
    int delete(Long id);


    /**
     * 通过主键查找
     */
    T selectByPrimaryKey(Long id);
}
