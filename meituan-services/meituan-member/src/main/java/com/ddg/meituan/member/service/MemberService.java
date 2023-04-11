package com.ddg.meituan.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.base.api.CommonResult;
import com.ddg.meituan.base.domain.UserDto;
import com.ddg.meituan.base.exception.MeituanSysException;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.member.entity.MemberEntity;
import com.ddg.meituan.member.vo.MemberRegisterVo;

/**
 * 会员
 *
 * @author 
 * @email 
 * @date 2021-01-31 16:44:02
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils<MemberEntity> queryPage(PageParam param);
    
    CommonResult<Long> register(MemberRegisterVo memberRegisterVo) throws MeituanSysException;

    boolean checkPhoneNum(String phoneNum);


    CommonResult<UserDto> loadUserByUsername(String username, String code) throws MeituanSysException;
}

