package com.ddg.meituan.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.member.entity.MemberCollectSpuEntity;

/**
 * 会员收藏的商品
 *
 * @author 
 * @email 
 * @date 2021-01-31 16:44:02
 */
public interface MemberCollectSpuService extends IService<MemberCollectSpuEntity> {

    PageUtils queryPage(PageParam param);
}

