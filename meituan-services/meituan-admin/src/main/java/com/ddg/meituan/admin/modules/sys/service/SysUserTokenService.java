

package com.ddg.meituan.admin.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.admin.modules.sys.domain.SysUserTokenEntity;
import com.ddg.meituan.base.api.CommonResult;

/**
 * 用户Token
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysUserTokenService extends IService<SysUserTokenEntity> {

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	CommonResult createToken(long userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(long userId);

}
