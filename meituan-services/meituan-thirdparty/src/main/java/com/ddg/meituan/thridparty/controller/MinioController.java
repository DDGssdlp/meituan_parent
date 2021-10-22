package com.ddg.meituan.thridparty.controller;

import com.ddg.meituan.common.utils.R;
import com.ddg.miniostarter.MinioHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

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
public class MinioController {

    @Resource
    private MinioHelper minioHelper;

    @PostMapping("/upload")
    public R uploadFile(@RequestParam("file") MultipartFile file){
        // 进行调用 service中的方法  返回的是文件的url地址

        String url = null;
        try {
            url = minioHelper.uploadFile(file.getInputStream(), file.getOriginalFilename(),
                    file.getContentType(), file.getSize());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.ok().put("data", url);
    }


}
