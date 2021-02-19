package com.ddg.meituan.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddg.meituan.common.exception.MeituanSysException;
import com.ddg.meituan.common.utils.PageUtils;
import com.ddg.meituan.common.utils.R;
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

    PageUtils queryPage(Map<String, Object> params);

    R register(MemberRegisterVo memberRegisterVo) throws MeituanSysException;

    boolean checkPhoneNum(String phoneNum);

    boolean checkUsername(String username);

    R login(MemberRegisterVo memberRegisterVo, HttpSession session) throws MeituanSysException;
}

