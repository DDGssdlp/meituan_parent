package com.ddg.meituan.admin.modules.job.controller;


import com.ddg.meituan.admin.modules.job.entity.ScheduleJobLogEntity;
import com.ddg.meituan.admin.modules.job.service.ScheduleJobLogService;
import com.ddg.meituan.base.api.CommonResult;
import com.ddg.meituan.base.utils.PageParam;
import com.ddg.meituan.base.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 定时任务日志
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {
	@Autowired
	private ScheduleJobLogService scheduleJobLogService;
	
	/**
	 * 定时任务日志列表
	 */
	@RequestMapping("/list")
	public CommonResult<PageUtils> list(PageParam params){
		PageUtils page = scheduleJobLogService.queryPage(params);
		
		return CommonResult.success(page);
	}
	
	/**
	 * 定时任务日志信息
	 */
	@RequestMapping("/info/{logId}")
	public CommonResult<ScheduleJobLogEntity> info(@PathVariable("logId") Long logId){
		ScheduleJobLogEntity log = scheduleJobLogService.getById(logId);
		
		return CommonResult.success(log);
	}
}
