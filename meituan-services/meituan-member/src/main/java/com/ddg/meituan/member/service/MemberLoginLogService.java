package com.ddg.meituan.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.member.entity.MemberLoginLogEntity;

/**
 * 会员登录记录
 *
 * @author 
 * @email 
 * @date 2021-01-31 16:44:02
 */
public interface MemberLoginLogService extends IService<MemberLoginLogEntity> {

    PageUtils queryPage(PageParam param);
}

