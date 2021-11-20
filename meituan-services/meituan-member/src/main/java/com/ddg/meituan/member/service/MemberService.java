package com.ddg.meituan.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.common.api.CommonResult;
import com.ddg.meituan.common.exception.MeituanSysException;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.common.utils.PageParam;
import com.ddg.meituan.member.entity.MemberEntity;
import com.ddg.meituan.member.vo.MemberRegisterVo;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 会员
 *
 * @author 
 * @email 
 * @date 2021-01-31 16:44:02
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(PageParam param);

    /**
     * 根据手机号获取member
     */
    MemberEntity getOneByPhone(String phone);

    CommonResult<Long> register(MemberRegisterVo memberRegisterVo) throws MeituanSysException;

    boolean checkPhoneNum(String phoneNum);

    boolean checkUsername(String username);

    CommonResult<MemberRegisterVo> login(MemberRegisterVo memberRegisterVo, HttpSession session) throws MeituanSysException;
}

