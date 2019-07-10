package com.macro.mall.config;

import com.aliyun.oss.OSSClient;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by macro on 2018/5/17.
 */
@Configuration
public class QCloudOssConfig {
    @Value("${qcloud.oss.accessKeyId}")
    private String QCLOUD_OSS_ACCESSKEYID;
    @Value("${qcloud.oss.accessKeySecret}")
    private String QCLOUD_OSS_ACCESSKEYSECRET;
    @Value("${qcloud.oss.bucketName}")
    private String QCLOUD_OSS_BUCKETNAME;

    @Bean
    public COSCredentials cosCredentials() {
        COSCredentials cred = new BasicCOSCredentials(QCLOUD_OSS_ACCESSKEYID, QCLOUD_OSS_ACCESSKEYSECRET);
        return cred;
    }
}
