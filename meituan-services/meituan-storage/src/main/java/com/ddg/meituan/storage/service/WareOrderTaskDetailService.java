package com.ddg.meituan.storage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.storage.entity.WareOrderTaskDetailEntity;

/**
 * 库存工作单
 *
 * @author Edison
 * @email wangzhijie0908@gmail.com
 * @date 2021-12-24 16:33:05
 */
public interface WareOrderTaskDetailService extends IService<WareOrderTaskDetailEntity> {

    PageUtils<WareOrderTaskDetailEntity> queryPage(PageParam param);
}

