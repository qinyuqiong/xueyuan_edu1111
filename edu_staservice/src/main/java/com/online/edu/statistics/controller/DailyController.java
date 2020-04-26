package com.online.edu.statistics.controller;


import com.online.edu.statistics.service.DailyService;
import com.online.edu.xueyuan_common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author lucy
 * @since 2020-04-26
 */
@CrossOrigin
@RestController
@RequestMapping("/statistics/daily")
public class DailyController {

    @Autowired
    private DailyService dailyService;

    @GetMapping("getStatisticDay/{day}")
    public R getStatisticDay(@PathVariable String day){
        dailyService.getCountRegisterNum(day);
        return R.ok();
    }

}

