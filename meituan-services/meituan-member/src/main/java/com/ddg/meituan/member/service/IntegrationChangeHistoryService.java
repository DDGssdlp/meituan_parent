package com.ddg.meituan.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.member.entity.IntegrationChangeHistoryEntity;

/**
 * 积分变化历史记录
 *
 * @author 
 * @email 
 * @date 2021-01-31 16:44:02
 */
public interface IntegrationChangeHistoryService extends IService<IntegrationChangeHistoryEntity> {

    PageUtils queryPage(PageParam param);
}

