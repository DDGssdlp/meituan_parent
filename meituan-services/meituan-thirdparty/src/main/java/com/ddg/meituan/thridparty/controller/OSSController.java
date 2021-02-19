package com.ddg.meituan.thridparty.controller;



import com.ddg.meituan.common.utils.R;
import com.ddg.meituan.thridparty.Service.OSSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
public class OSSController {

    @Autowired
    private OSSService ossService;


    @PostMapping("/upload")
    public R uploadFile(MultipartFile file){
        // 进行调用 service中的方法  返回的是文件的url地址
        String url =  ossService.uploadFileAvatar(file);


        return R.ok().put("data", url);
    }





}
