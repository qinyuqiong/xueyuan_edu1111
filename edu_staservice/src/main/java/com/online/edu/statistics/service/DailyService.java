package com.online.edu.statistics.service;

import com.online.edu.statistics.entity.Daily;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author lucy
 * @since 2020-04-26
 */
public interface DailyService extends IService<Daily> {

    void getCountRegisterNum(String day);
}
