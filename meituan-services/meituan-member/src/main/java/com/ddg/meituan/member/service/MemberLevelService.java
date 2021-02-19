package com.ddg.meituan.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.member.entity.MemberLevelEntity;

import java.util.Map;

/**
 * 会员等级
 *
 * @author 
 * @email 
 * @date 2021-01-31 16:44:02
 */
public interface MemberLevelService extends IService<MemberLevelEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

