package com.ddg.meituan.admin.modules.job.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.admin.modules.job.dao.ScheduleJobDao;
import com.ddg.meituan.admin.modules.job.entity.ScheduleJobEntity;
import com.ddg.meituan.admin.modules.job.service.ScheduleJobService;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.base.utils.PageUtils;
import org.springframework.stereotype.Service;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author wzj
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/12/20 16:49
 * @email: wangzhijie0908@gmail.com
 */
@Service
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobDao, ScheduleJobEntity> implements ScheduleJobService {
    @Override
    public PageUtils queryPage(PageParam params) {
        return null;
    }

    @Override
    public void saveJob(ScheduleJobEntity scheduleJob) {

    }

    @Override
    public void update(ScheduleJobEntity scheduleJob) {

    }

    @Override
    public void deleteBatch(Long[] jobIds) {

    }

    @Override
    public int updateBatch(Long[] jobIds, int status) {
        return 0;
    }

    @Override
    public void run(Long[] jobIds) {

    }

    @Override
    public void pause(Long[] jobIds) {

    }

    @Override
    public void resume(Long[] jobIds) {

    }
}
