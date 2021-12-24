package com.ddg.meituan.admin.modules.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.admin.modules.sys.dao.SysLogDao;
import com.ddg.meituan.admin.modules.sys.entity.SysLogEntity;
import com.ddg.meituan.admin.modules.sys.service.SysLogService;
import com.ddg.meituan.common.utils.PageParam;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.common.utils.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("sysLogService")
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLogEntity> implements SysLogService {

    @Override
    public PageUtils queryPage(PageParam param) {
        String key = null;

        IPage<SysLogEntity> page = this.page(
            new Query<SysLogEntity>().getPage(param),
            new QueryWrapper<SysLogEntity>().like(StringUtils.isNotBlank(key),"username", key)
        );

        return new PageUtils(page);
    }
}
