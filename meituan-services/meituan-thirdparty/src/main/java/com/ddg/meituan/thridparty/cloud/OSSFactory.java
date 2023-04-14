package com.ddg.meituan.thridparty.cloud;

import com.ddg.meituan.thridparty.service.SysConfigService;
import com.ddg.meituan.thridparty.constant.ConfigConstant;
import com.ddg.meituan.thridparty.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


//@Component
public final class OSSFactory {


    @Value("meituan.oss.cloud.type")
    private Integer type;

    private final SysConfigService sysConfigService;

    @Autowired
    public OSSFactory(SysConfigService sysConfigService) {
        this.sysConfigService = sysConfigService;
    }


    public CloudStorageService build(){
        //获取云存储配置信息
        CloudStorageConfig config = sysConfigService.getConfigObject(ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);

        if(config.getType() == Constant.CloudService.QINIU.getValue()){
            return new QiniuCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.ALIYUN.getValue()){
            return new AliyunCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.QCLOUD.getValue()){
            return new QcloudCloudStorageService(config);
        }

        return null;
    }

}
