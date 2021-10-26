package com.ddg.meituan.thridparty.controller;

import com.alibaba.fastjson.JSON;
import com.ddg.meituan.common.api.CommonResult;
import com.ddg.meituan.common.constant.AuthConstant;
import com.ddg.meituan.common.domain.UserDto;
import com.ddg.miniostarter.MinioHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.ws.rs.GET;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author wzj
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/10/22 10:46
 * @email: wangzhijie0908@gmail.com
 */
@RestController
@RequestMapping("/thirdparty/minio")
@Api(value = "minio 文件上传")
@Slf4j
public class MinioController {

    @Resource
    private MinioHelper minioHelper;

    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public CommonResult<String> uploadFile(@RequestHeader(value = AuthConstant.USER_TOKEN_HEADER, required = false) String userInfo,
                                           @RequestParam("file") MultipartFile file){
        // 进行调用 service中的方法  返回的是文件的url地址
        if(!StringUtils.isEmpty(userInfo)){
            UserDto userDto = JSON.parseObject(userInfo, UserDto.class);
            System.out.println(userDto);
        }

        String url = null;
        try {
            url = minioHelper.uploadFile(file.getInputStream(), file.getOriginalFilename(),
                    file.getContentType(), file.getSize());
        } catch (Exception e) {
            log.error("MinioController uploadFile error = ", e);
        }
        return CommonResult.success(url);
    }


}
