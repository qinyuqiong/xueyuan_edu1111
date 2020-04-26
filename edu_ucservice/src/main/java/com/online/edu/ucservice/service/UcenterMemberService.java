package com.online.edu.ucservice.service;

import com.online.edu.ucservice.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-04-26
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    Integer countRegisterNum(String day);
}
