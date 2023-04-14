package com.ddg.meituan.admin.modules.job.service.impl;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.admin.modules.job.dao.ScheduleJobLogDao;
import com.ddg.meituan.admin.modules.job.entity.ScheduleJobLogEntity;
import com.ddg.meituan.admin.modules.job.service.ScheduleJobLogService;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.base.utils.PageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;


@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogDao, ScheduleJobLogEntity> implements ScheduleJobLogService {

	@Override
	public PageUtils<ScheduleJobLogEntity> queryPage(PageParam param) {
		String jobId = null;//(String)params.get("jobId");

		IPage<ScheduleJobLogEntity> page = this.page(
			new Query<ScheduleJobLogEntity>().getPage(param),
			new QueryWrapper<ScheduleJobLogEntity>().like(StringUtils.isNotBlank(jobId),"job_id", jobId)
		);

		return PageUtils.of(page);
	}

}
