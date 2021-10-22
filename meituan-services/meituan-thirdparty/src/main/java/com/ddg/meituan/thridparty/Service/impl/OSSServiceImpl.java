package com.ddg.meituan.thridparty.Service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.ddg.meituan.thridparty.Service.OSSService;
import com.ddg.meituan.thridparty.component.OSSConfigurationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/1/30 18:16
 * @email: wangzhijie0908@gmail.com
 */
@Service
@Slf4j
public class OSSServiceImpl implements OSSService {

    @Autowired
    private OSSConfigurationProperties ossProperties;

    @Override
    public String uploadFileAvatar(MultipartFile file) {

        if (file == null) {
            return null;
        }
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ossProperties.getEndpoint();
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ossProperties.getKeyId();
        String accessKeySecret = ossProperties.getKeySecret();
        String bucketName = ossProperties.getBucketName();
        String uuid = UUID.randomUUID().toString().replace("-", "");

        // 将传入的文件按照时间进行分类 也就是按照日期分类：使用的是工具类进行时间的获取
        String datePath = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")).toString();
        // 这里的文件名称是加上后缀名称的所以需要将uuid添加到前面

        String fileName = datePath+"/"+uuid + file.getOriginalFilename();

        log.info(endpoint + accessKeyId + accessKeySecret + bucketName + fileName);

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流。
        InputStream inputStream = null;
        StringBuilder sb = null;
        try {
            inputStream = file.getInputStream();
            // 三种参数 1 就是bucket名称 2 就是文件的名称 或者是带有路径的名称 3 就是流
            ossClient.putObject(bucketName, fileName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            // 最后的就是将路径返回
            sb = new StringBuilder("https://");
            // 进行路径的拼接
            sb.append(bucketName).append(".").append(endpoint).append("/").append(fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return sb.toString();

    }
}
