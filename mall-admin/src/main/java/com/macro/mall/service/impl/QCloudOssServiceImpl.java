package com.macro.mall.service.impl;

import com.macro.mall.dto.OssPolicyResult;
import com.macro.mall.service.OssService;
import com.macro.mall.service.QCloudOssService;
import com.qcloud.cos.auth.COSCredentials;
import com.tencent.cloud.CosStsClient;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.TreeMap;

/**
 * qcloudoss上传管理Service实现类
 * Created by macro on 2018/5/17.
 */
@Service
public class QCloudOssServiceImpl implements QCloudOssService {

	private static final Logger LOGGER = LoggerFactory.getLogger(QCloudOssServiceImpl.class);
	@Value("${qcloud.oss.policy.expire}")
	private int QCLOUD_OSS_EXPIRE;
	@Value("${qcloud.oss.maxSize}")
	private int QCLOUD_OSS_MAX_SIZE;
	@Value("${qcloud.oss.bucketName}")
	private String QCLOUD_OSS_BUCKETNAME;
	@Value("${qcloud.oss.region}")
	private String QCLOUD_OSS_REGION;
	@Value("${qcloud.oss.dir.prefix}")
	private String QCLOUD_OSS_DIR_PREFIX;
	@Value("${qcloud.oss.accessKeyId}")
	private String QCLOUD_OSS_ACCESSKEYID;
	@Value("${qcloud.oss.accessKeySecret}")
	private String QCLOUD_OSS_ACCESSKEYSECRET;

//	@Autowired
//	private COSCredentials cosCredentials;

	/**
	 * 签名生成
	 */
	@Override
	public JSONObject policy() {
		TreeMap<String, Object> config = new TreeMap<>();
		try {
			// 替换为您的 SecretId
			config.put("SecretId", QCLOUD_OSS_ACCESSKEYID);
			// 替换为您的 SecretKey
			config.put("SecretKey", QCLOUD_OSS_ACCESSKEYSECRET);

			// 临时密钥有效时长，单位是秒
			config.put("durationSeconds", 1800);

			// 换成您的 bucket
			config.put("bucket", QCLOUD_OSS_BUCKETNAME);
			// 换成 bucket 所在地区
			config.put("region", QCLOUD_OSS_REGION);

			// 这里改成允许的路径前缀，可以根据自己网站的用户登录态判断允许上传的目录，例子：* 或者 doc/* 或者 picture.jpg
			config.put("allowPrefix", "*");

			// 密钥的权限列表。简单上传、表单上传和分片上传需要以下的权限，其他权限列表请看 https://cloud.tencent.com/document/product/436/31923
			String[] allowActions = new String[] {
					// 简单上传
					"name/cos:PutObject",
//					// 表单上传、小程序上传
//					"name/cos:PostObject",
//					// 分片上传
//					"name/cos:InitiateMultipartUpload",
//					"name/cos:ListMultipartUploads",
//					"name/cos:ListParts",
//					"name/cos:UploadPart",
//					"name/cos:CompleteMultipartUpload"
			};
			config.put("allowActions", allowActions);

			JSONObject credential = CosStsClient.getCredential(config);
			credential.put("bucket", QCLOUD_OSS_BUCKETNAME);
			credential.put("region", QCLOUD_OSS_REGION);
			// 换成 bucket 所在地区
			credential.put("dirPrefix", QCLOUD_OSS_DIR_PREFIX);
			//成功返回临时密钥信息，如下打印密钥信息
			System.out.println(credential);
			return credential;
		} catch (Exception e) {
			e.printStackTrace();
			//失败抛出异常
			throw new IllegalArgumentException("no valid secret !");
		}
	}

}
