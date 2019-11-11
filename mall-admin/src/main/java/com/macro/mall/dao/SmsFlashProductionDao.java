package com.macro.mall.dao;

import com.macro.mall.dto.SmsFlashProductionResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SmsFlashProductionDao {
    /**
     * 获取限时购及相关商品信息
     */
    List<SmsFlashProductionResult> getListByFlash(@Param("flashPromotionId") Long flashPromotionId);
}
