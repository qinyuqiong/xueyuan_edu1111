package com.online.edu.ucservice.service.impl;

import com.online.edu.ucservice.entity.UcenterMember;
import com.online.edu.ucservice.mapper.UcenterMemberMapper;
import com.online.edu.ucservice.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-04-26
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Override
    public Integer countRegisterNum(String day) {
        return baseMapper.countRegisterNum(day);
    }
}
