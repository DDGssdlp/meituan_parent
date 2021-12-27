package com.ddg.meituan.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.common.utils.PageParam;
import com.ddg.meituan.product.entity.AttrEntity;
import com.ddg.meituan.product.vo.AttrRespVo;

import java.util.Map;

/**
 * 商品属性
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-26 13:36:54
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils<AttrEntity> queryPage(PageParam param);

    PageUtils<AttrRespVo> queryBaseAttrPage(PageParam pageParam, String type);
}

