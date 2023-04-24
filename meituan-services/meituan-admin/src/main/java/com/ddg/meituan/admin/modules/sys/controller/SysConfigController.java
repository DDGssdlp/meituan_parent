package com.ddg.meituan.admin.modules.sys.controller;


import com.ddg.meituan.admin.common.annotation.SysLog;
import com.ddg.meituan.admin.common.annotation.validator.ValidatorUtils;
import com.ddg.meituan.admin.modules.sys.domain.SysConfigEntity;
import com.ddg.meituan.admin.modules.sys.service.SysConfigService;
import com.ddg.meituan.base.api.CommonResult;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.base.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 系统配置信息
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/config")
public class SysConfigController  {

	private final SysConfigService sysConfigService;

	@Autowired
	public SysConfigController(SysConfigService sysConfigService) {
		this.sysConfigService = sysConfigService;
	}

	/**
	 * 所有配置列表
	 */
	@GetMapping("/list")
	public CommonResult<PageUtils<SysConfigEntity>> list(PageParam pageParam){
		PageUtils<SysConfigEntity> page = sysConfigService.queryPage(pageParam);

		return CommonResult.success(page);
	}
	
	
	/**
	 * 配置信息
	 */
	@GetMapping("/info/{id}")
	public CommonResult<SysConfigEntity> info(@PathVariable("id") Long id){
		SysConfigEntity config = sysConfigService.getById(id);
		
		return CommonResult.success(config);
	}
	
	/**
	 * 保存配置
	 */
	@SysLog("保存配置")
	@PostMapping("/save")
	public CommonResult<Object> save(@RequestBody SysConfigEntity config){
		ValidatorUtils.validateEntity(config);

		sysConfigService.saveConfig(config);
		
		return CommonResult.success();
	}
	
	/**
	 * 修改配置
	 */
	@SysLog("修改配置")
	@PostMapping("/update")
	public CommonResult<Object> update(@RequestBody SysConfigEntity config){
		ValidatorUtils.validateEntity(config);
		
		sysConfigService.update(config);
		
		return CommonResult.success();
	}
	
	/**
	 * 删除配置
	 */
	@SysLog("删除配置")
	@PostMapping("/delete")
	public CommonResult<Object> delete(@RequestBody Long[] ids){
		sysConfigService.deleteBatch(ids);
		
		return CommonResult.success();
	}

}
