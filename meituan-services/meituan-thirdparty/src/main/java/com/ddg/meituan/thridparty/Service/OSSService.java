package com.ddg.meituan.thridparty.Service;

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
 * @date 2021/1/30 18:16
 * @email: wangzhijie0908@gmail.com
 */

public interface OSSService {
    String uploadFileAvatar(MultipartFile file);
}
