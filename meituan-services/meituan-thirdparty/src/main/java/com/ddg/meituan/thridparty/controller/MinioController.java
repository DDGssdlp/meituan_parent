package com.ddg.meituan.thridparty.controller;

import com.ddg.meituan.base.api.CommonResult;
import com.ddg.miniostarter.MinioHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


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
@RequestMapping("/minio")
@Api(value = "minio 文件上传")
@Slf4j
public class MinioController {

    private final MinioHelper minioHelper;

    public MinioController(MinioHelper minioHelper) {
        this.minioHelper = minioHelper;
    }

    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public CommonResult<String> uploadFile(@RequestParam("file") MultipartFile file) {
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
