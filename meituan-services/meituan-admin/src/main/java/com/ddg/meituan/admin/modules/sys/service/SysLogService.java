package com.ddg.meituan.admin.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.admin.modules.sys.entity.SysLogEntity;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.base.utils.PageUtils;



/**
 * 系统日志
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils<SysLogEntity> queryPage(PageParam params);

}
