

package com.ddg.meituan.admin.modules.sys.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.admin.modules.sys.dao.SysConfigDao;
import com.ddg.meituan.admin.modules.sys.entity.SysConfigEntity;
import com.ddg.meituan.admin.modules.sys.service.SysConfigService;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Map;

@Service("sysConfigService")
public class SysConfigServiceImpl extends ServiceImpl<SysConfigDao, SysConfigEntity> implements SysConfigService {

	@Override
	public Page queryPage(Map<String, Object> params) {
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
