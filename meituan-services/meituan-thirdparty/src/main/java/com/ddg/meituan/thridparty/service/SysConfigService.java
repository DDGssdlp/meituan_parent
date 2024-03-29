
package com.ddg.meituan.thridparty.service;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.thridparty.entity.ConfigEntity;


/**
 * 系统配置信息
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysConfigService  {

	Page<ConfigEntity> queryPage(PageParam param);
	
	/**
	 * 保存配置信息
	 */
	public void saveConfig(ConfigEntity config);
	
	/**
	 * 更新配置信息
	 */
	public void update(ConfigEntity config);
	
	/**
	 * 根据key，更新value
	 */
	public void updateValueByKey(String key, String value);
	
	/**
	 * 删除配置信息
	 */
	public void deleteBatch(Long[] ids);
	
	/**
	 * 根据key，获取配置的value值
	 * 
	 * @param key           key
	 */
	public String getValue(String key);
	
	/**
	 * 根据key，获取value的Object对象
	 * @param key    key
	 * @param clazz  Object对象
	 */
	public <T> T getConfigObject(String key, Class<T> clazz);
	
}
