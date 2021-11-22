

package com.ddg.meituan.admin.modules.sys.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.admin.modules.sys.dao.SysConfigDao;
import com.ddg.meituan.admin.modules.sys.entity.SysConfigEntity;
import com.ddg.meituan.admin.modules.sys.service.SysConfigService;

import com.ddg.meituan.common.utils.PageParam;
import com.ddg.meituan.common.utils.PageUtils;
import org.springframework.stereotype.Service;


@Service("sysConfigService")
public class SysConfigServiceImpl extends ServiceImpl<SysConfigDao, SysConfigEntity> implements SysConfigService {

	@Override
	public PageUtils queryPage(PageParam param) {
		return null;
	}

	@Override
	public void saveConfig(SysConfigEntity config) {

	}

	@Override
	public void update(SysConfigEntity config) {

	}

	@Override
	public void updateValueByKey(String key, String value) {

	}

	@Override
	public void deleteBatch(Long[] ids) {

	}

	@Override
	public String getValue(String key) {
		return null;
	}

	@Override
	public <T> T getConfigObject(String key, Class<T> clazz) {
		return null;
	}
}
