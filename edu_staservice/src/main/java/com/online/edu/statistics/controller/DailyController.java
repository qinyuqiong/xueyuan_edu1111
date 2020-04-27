package com.online.edu.statistics.controller;


import com.online.edu.statistics.service.DailyService;
import com.online.edu.xueyuan_common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    /**
     * 返回图标显示使用的数据
     * 第一部分时间：['2019-01-02','2019-02-29']
     * 第二部分数量：[3,4]
     * @return
     */
    @GetMapping("getCountData/{type}/{begin}/{end}")
    public R getDataCount(@PathVariable String type , @PathVariable String begin ,@PathVariable String end){
        Map<String,Object> map = dailyService.getDataCount(type,begin,end);
        return R.ok().data("mapData",map);
    }

}

