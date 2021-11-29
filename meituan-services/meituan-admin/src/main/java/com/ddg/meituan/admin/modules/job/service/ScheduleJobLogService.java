package com.ddg.meituan.admin.modules.job.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.admin.modules.job.entity.ScheduleJobLogEntity;
import com.ddg.meituan.common.utils.PageParam;
import com.ddg.meituan.common.utils.PageUtils;



/**
 * 定时任务日志
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {

	PageUtils queryPage(PageParam params);
	
}