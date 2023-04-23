package com.ddg.meituan.admin.modules.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.admin.modules.sys.dao.SysLogDao;
import com.ddg.meituan.admin.modules.sys.domain.SysLogEntity;
import com.ddg.meituan.admin.modules.sys.service.SysLogService;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.base.api.Query;
import com.ddg.meituan.base.utils.PageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;


@Service("sysLogService")
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLogEntity> implements SysLogService {

    @Override
    public PageUtils<SysLogEntity> queryPage(PageParam param) {
        String key = param.getKey();

        IPage<SysLogEntity> page = this.page(
            new Query<SysLogEntity>().getPage(param),
            new QueryWrapper<SysLogEntity>().like(StringUtils.isNotBlank(key),"username", key)
        );

        return PageUtils.of(page);
    }
}
