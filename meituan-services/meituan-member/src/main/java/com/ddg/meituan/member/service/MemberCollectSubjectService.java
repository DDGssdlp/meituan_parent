package com.ddg.meituan.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.member.entity.MemberCollectSubjectEntity;

/**
 * 会员收藏的专题活动
 *
 * @author 
 * @email 
 * @date 2021-01-31 16:44:02
 */
public interface MemberCollectSubjectService extends IService<MemberCollectSubjectEntity> {

    PageUtils queryPage(PageParam param);
}

