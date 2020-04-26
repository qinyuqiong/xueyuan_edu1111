package com.online.edu.ucservice.controller;


import com.online.edu.ucservice.service.UcenterMemberService;
import com.online.edu.xueyuan_common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-04-26
 */
@CrossOrigin
@RestController
@RequestMapping("/ucservice/ucenter-member")
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    @GetMapping("countRegisterNum/{day}")
    public R countRegisterNum(@PathVariable String day){
        Integer result  = ucenterMemberService.countRegisterNum(day);
        return R.ok().data("result",result);
    }

}

