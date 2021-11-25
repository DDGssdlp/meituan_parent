package com.ddg.meituan.admin.modules.job.controller;


import com.ddg.meituan.admin.common.annotation.SysLog;
import com.ddg.meituan.admin.common.annotation.validator.ValidatorUtils;
import com.ddg.meituan.admin.modules.job.entity.ScheduleJobEntity;
import com.ddg.meituan.admin.modules.job.service.ScheduleJobService;
import com.ddg.meituan.common.api.CommonResult;
import com.ddg.meituan.common.utils.PageParam;
import com.ddg.meituan.common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 定时任务
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/schedule")
public class ScheduleJobController {
	@Autowired
	private ScheduleJobService scheduleJobService;
	
	/**
	 * 定时任务列表
	 */
	@RequestMapping("/list")
	public CommonResult<PageUtils> list(PageParam params){
		PageUtils page = scheduleJobService.queryPage(params);

		return CommonResult.success(page);
	}
	
	/**
	 * 定时任务信息
	 */
	@RequestMapping("/info/{jobId}")
	public CommonResult<ScheduleJobEntity> info(@PathVariable("jobId") Long jobId){
		ScheduleJobEntity schedule = scheduleJobService.getById(jobId);
		
		return CommonResult.success(schedule);
	}
	
	/**
	 * 保存定时任务
	 */
	@SysLog("保存定时任务")
	@RequestMapping("/save")
	public CommonResult<Object> save(@RequestBody ScheduleJobEntity scheduleJob){
		ValidatorUtils.validateEntity(scheduleJob);
		
		scheduleJobService.saveJob(scheduleJob);
		
		return CommonResult.success();
	}
	
	/**
	 * 修改定时任务
	 */
	@SysLog("修改定时任务")
	@RequestMapping("/update")
	public CommonResult<Object> update(@RequestBody ScheduleJobEntity scheduleJob){
		ValidatorUtils.validateEntity(scheduleJob);
				
		scheduleJobService.update(scheduleJob);
		
		return CommonResult.success();
	}
	
	/**
	 * 删除定时任务
	 */
	@SysLog("删除定时任务")
	@RequestMapping("/delete")
	public CommonResult<Object> delete(@RequestBody Long[] jobIds){
		scheduleJobService.deleteBatch(jobIds);
		
		return CommonResult.success();
	}
	
	/**
	 * 立即执行任务
	 */
	@SysLog("立即执行任务")
	@RequestMapping("/run")
	public CommonResult<Object> run(@RequestBody Long[] jobIds){
		scheduleJobService.run(jobIds);
		
		return CommonResult.success();
	}
	
	/**
	 * 暂停定时任务
	 */
	@SysLog("暂停定时任务")
	@RequestMapping("/pause")
	public CommonResult<Object> pause(@RequestBody Long[] jobIds){
		scheduleJobService.pause(jobIds);
		
		return CommonResult.success();
	}
	
	/**
	 * 恢复定时任务
	 */
	@SysLog("恢复定时任务")
	@RequestMapping("/resume")
	public CommonResult<Object> resume(@RequestBody Long[] jobIds){
		scheduleJobService.resume(jobIds);
		
		return CommonResult.success();
	}

}
