package com.ddg.meituan.admin.common.aspect;

import com.alibaba.fastjson.JSON;
import com.ddg.meituan.admin.common.annotation.SysLog;
import com.ddg.meituan.admin.common.utils.HttpContextUtils;
import com.ddg.meituan.admin.modules.sys.domain.SysLogEntity;
import com.ddg.meituan.admin.modules.sys.service.SysLogService;
import com.ddg.meituan.base.constant.BaseConstant;
import com.ddg.meituan.base.domain.UserDto;
import com.ddg.meituan.base.utils.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;


/**
 * 系统日志，切面处理类
 *
 * @author Mark sunlightcs@gmail.com
 */
@Aspect
@Component
@Slf4j
public class SysLogAspect {

	private final SysLogService sysLogService;

	@Autowired
	public SysLogAspect(SysLogService sysLogService) {
		this.sysLogService = sysLogService;
	}


	@Pointcut("@annotation(com.ddg.meituan.admin.common.annotation.SysLog)")
	public void logPointCut() { 
		
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;

		//保存日志
		saveSysLog(point, time);

		return result;
	}

	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		SysLogEntity sysLog = new SysLogEntity();
		SysLog syslog = method.getAnnotation(SysLog.class);
		if(syslog != null){
			//注解上的描述
			sysLog.setOperation(syslog.value());
		}

		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");

		//请求的参数
		Object[] args = joinPoint.getArgs();
		try{
			sysLog.setParams(JSON.toJSONString(args));

			//获取request
			HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
			//设置IP地址
			sysLog.setIp(IPUtils.getIpAddr(request));

			//用户名
			String user = request.getHeader(BaseConstant.USER_TOKEN_HEADER);

			UserDto userDto = JSON.parseObject(user, UserDto.class);

			sysLog.setUsername(userDto.getUsername());

			sysLog.setTime(time);
			sysLog.setCreateDate(LocalDateTime.now());
			//保存系统日志
			sysLogService.save(sysLog);
		}catch (Exception e){
			log.error("保存日志失败");
		}


	}
}
