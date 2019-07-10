package com.macro.mall.service;

import org.json.JSONObject;

/**
 * oss上传管理Service
 * Created by macro on 2018/5/17.
 */
public interface QCloudOssService {
    /**
     * oss上传策略生成
     */
    JSONObject policy();
}
