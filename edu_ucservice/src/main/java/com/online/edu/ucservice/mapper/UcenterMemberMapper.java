package com.online.edu.ucservice.mapper;

import com.online.edu.ucservice.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-04-26
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    public Integer countRegisterNum(String day);

}
