package com.ddg.meituan.thridparty.controller;

import com.ddg.meituan.base.api.CommonResult;
import com.ddg.meituan.thridparty.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/1/30 17:58
 * @email: wangzhijie0908@gmail.com
 */
@RestController
@RequestMapping("/oss")
@Api("阿里云oss 文件上传")
public class OSSController {

    private final OssService ossService;

    @Autowired
    public OSSController(OssService ossService) {
        this.ossService = ossService;
    }


    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public CommonResult<String> uploadFile(MultipartFile file){
        // 进行调用 service中的方法  返回的是文件的url地址

        String url =  ossService.uploadFileAvatar(file);
        return CommonResult.success(url);
    }





}
