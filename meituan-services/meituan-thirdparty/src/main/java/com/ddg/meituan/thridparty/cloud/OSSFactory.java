package com.ddg.meituan.thridparty.cloud;

import com.ddg.meituan.common.utils.ConfigConstant;
import com.ddg.meituan.common.utils.Constant;
import com.ddg.meituan.common.utils.SpringContextUtils;
import com.ddg.meituan.thridparty.Service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


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
