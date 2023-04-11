

package com.ddg.meituan.thridparty.Service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.thridparty.Service.SysConfigService;
import com.ddg.meituan.thridparty.entity.ConfigEntity;

import org.springframework.stereotype.Service;

@Service("sysConfigService")
public class SysConfigServiceImpl  implements SysConfigService {

	@Override
	public Page<ConfigEntity> queryPage(PageParam param) {
		return null;
	}

	@Override
	public void saveConfig(ConfigEntity config) {

	}

	@Override
	public void update(ConfigEntity config) {

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
