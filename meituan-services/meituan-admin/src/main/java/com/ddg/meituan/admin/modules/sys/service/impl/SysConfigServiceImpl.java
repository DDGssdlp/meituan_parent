package com.ddg.meituan.admin.modules.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.admin.modules.sys.dao.SysConfigDao;
import com.ddg.meituan.admin.modules.sys.entity.SysConfigEntity;
import com.ddg.meituan.admin.modules.sys.redis.SysConfigRedis;
import com.ddg.meituan.admin.modules.sys.service.SysConfigService;

import com.ddg.meituan.base.exception.MeituanSysException;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.base.utils.PageUtils;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 *
 * @author DELL
 */
@Service("sysConfigService")
public class SysConfigServiceImpl extends ServiceImpl<SysConfigDao, SysConfigEntity> implements SysConfigService {

	private final SysConfigRedis sysConfigRedis;

	public SysConfigServiceImpl(SysConfigRedis sysConfigRedis) {
		this.sysConfigRedis = sysConfigRedis;
	}

	@Override
	public PageUtils<SysConfigEntity> queryPage(PageParam param) {

		String key = param.getKey();
		IPage<SysConfigEntity> page = this.page(
				new Query<SysConfigEntity>().getPage(param),
				new QueryWrapper<SysConfigEntity>()
						.like(StringUtils.isNotBlank(key),"param_key", key)
						.eq("status", 1)
		);

		return PageUtils.of(page);
	}

	@Override
	public void saveConfig(SysConfigEntity config) {
		this.save(config);
		sysConfigRedis.saveOrUpdate(config);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysConfigEntity config) {
		this.updateById(config);
		sysConfigRedis.saveOrUpdate(config);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateValueByKey(String key, String value) {
		baseMapper.updateValueByKey(key, value);
		sysConfigRedis.delete(key);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteBatch(Long[] ids) {
		for(Long id : ids){
			SysConfigEntity config = this.getById(id);
			sysConfigRedis.delete(config.getParamKey());
		}

		this.removeByIds(Arrays.asList(ids));
	}

	@Override
	public String getValue(String key) {
		SysConfigEntity config = sysConfigRedis.get(key);
		if(config == null){
			config = baseMapper.queryByKey(key);
			sysConfigRedis.saveOrUpdate(config);
		}

		return config == null ? null : config.getParamValue();
	}

	@Override
	public <T> T getConfigObject(String key, Class<T> clazz) {
		String value = getValue(key);
		if(StringUtils.isNotBlank(value)){
			return new Gson().fromJson(value, clazz);
		}

		try {
			return clazz.newInstance();
		} catch (Exception e) {
			throw new MeituanSysException("获取参数失败");
		}
	}
}
