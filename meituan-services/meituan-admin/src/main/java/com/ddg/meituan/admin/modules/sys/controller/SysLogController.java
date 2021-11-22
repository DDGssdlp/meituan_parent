package com.ddg.meituan.admin.modules.sys.controller;


import com.ddg.meituan.admin.modules.sys.service.SysLogService;
import com.ddg.meituan.common.api.CommonResult;
import com.ddg.meituan.common.utils.PageParam;
import com.ddg.meituan.common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统日志
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/log")
public class SysLogController {

	private final SysLogService sysLogService;

	@Autowired
	public SysLogController(SysLogService sysLogService) {
		this.sysLogService = sysLogService;
	}

	/**
	 * 列表
	 */

	@GetMapping("/list")
	public CommonResult<PageUtils> list(PageParam params){
		PageUtils page = sysLogService.queryPage(params);

		return CommonResult.success(page);
	}
	
}
