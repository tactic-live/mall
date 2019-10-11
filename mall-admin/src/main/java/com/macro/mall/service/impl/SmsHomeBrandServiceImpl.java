package com.macro.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.mapper.SmsHomeBrandMapper;
import com.macro.mall.model.SmsHomeBrand;
import com.macro.mall.model.SmsHomeBrandExample;
import com.macro.mall.service.SmsHomeBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页品牌管理Service实现类
 * Created by macro on 2018/11/6.
 */
@Service
public class SmsHomeBrandServiceImpl implements SmsHomeBrandService {
	@Autowired
	private SmsHomeBrandMapper homeBrandMapper;

	@Override
	public List<SmsHomeBrand> create(List<SmsHomeBrand> homeBrandList) {
		List<Long> brandIdList = new ArrayList<Long>();
		for (SmsHomeBrand smsHomeBrand : homeBrandList) {
			smsHomeBrand.setRecommendStatus(1);
			smsHomeBrand.setSort(0);
			homeBrandMapper.insert(smsHomeBrand);

			brandIdList.add(smsHomeBrand.getBrandId());
		}

		// 返回添加的结果
		return homeBrandMapper.selectByBrandIdList(brandIdList);
	}

	@Override
	public int updateSort(Long id, Integer sort) {
		SmsHomeBrand homeBrand = new SmsHomeBrand();
		homeBrand.setId(id);
		homeBrand.setSort(sort);
		return homeBrandMapper.updateByPrimaryKeySelective(homeBrand);
	}

	@Override
	public int delete(List<Long> ids) {
		SmsHomeBrandExample example = new SmsHomeBrandExample();
		example.createCriteria().andIdIn(ids);
		return homeBrandMapper.deleteByExample(example);
	}

	@Override
	public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
		SmsHomeBrandExample example = new SmsHomeBrandExample();
		example.createCriteria().andIdIn(ids);
		SmsHomeBrand record = new SmsHomeBrand();
		record.setRecommendStatus(recommendStatus);
		return homeBrandMapper.updateByExampleSelective(record, example);
	}

	@Override
	public List<SmsHomeBrand> list(String brandName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
//		SmsHomeBrandExample example = new SmsHomeBrandExample();
//		SmsHomeBrandExample.Criteria criteria = example.createCriteria();
//		if (!StringUtils.isEmpty(brandName)) {
//			criteria.andBrandNameLike("%" + brandName + "%");
//		}
//		if (recommendStatus != null) {
//			if (recommendStatus == 0) {
//				criteria.andRecommendStatusNotEqualTo(1);
//			} else {
//				criteria.andRecommendStatusEqualTo(recommendStatus);
//			}
//		}
//		example.setOrderByClause("sort desc");
		return homeBrandMapper.selectByExample(brandName, recommendStatus);
	}
}
