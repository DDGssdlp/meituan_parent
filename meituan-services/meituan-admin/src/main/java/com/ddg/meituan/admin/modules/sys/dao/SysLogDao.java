

package com.ddg.meituan.admin.modules.sys.dao;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddg.meituan.admin.modules.sys.domain.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {
	
}
