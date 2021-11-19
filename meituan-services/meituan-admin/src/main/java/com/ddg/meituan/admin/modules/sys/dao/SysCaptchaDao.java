package com.ddg.meituan.admin.modules.sys.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddg.meituan.admin.modules.sys.entity.SysCaptchaEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 验证码
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysCaptchaDao extends BaseMapper<SysCaptchaEntity>  {

}
