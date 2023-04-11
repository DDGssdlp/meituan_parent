package com.ddg.meituan.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.member.entity.MemberStatisticsInfoEntity;

/**
 * 会员统计信息
 *
 * @author 
 * @email 
 * @date 2021-01-31 16:44:02
 */
public interface MemberStatisticsInfoService extends IService<MemberStatisticsInfoEntity> {

    PageUtils queryPage(PageParam param);
}

