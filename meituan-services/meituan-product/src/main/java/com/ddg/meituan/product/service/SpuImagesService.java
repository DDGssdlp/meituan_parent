package com.ddg.meituan.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.product.entity.SpuImagesEntity;

import java.util.List;

/**
 * spu图片
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-26 13:36:54
 */
public interface SpuImagesService extends IService<SpuImagesEntity> {

    PageUtils<SpuImagesEntity> queryPage(PageParam param);

    void saveImages(Long id, List<String> images);
}

