package com.online.edu.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.statistics.client.UcenterClient;
import com.online.edu.statistics.entity.Daily;
import com.online.edu.statistics.mapper.DailyMapper;
import com.online.edu.statistics.service.DailyService;
import com.online.edu.xueyuan_common.R;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author lucy
 * @since 2020-04-26
 */
@Service
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements DailyService {

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public void getCountRegisterNum(String day) {
        //判断再进行添加统计分析表里面是否存在添加的天数的记录，如果存在删除，
        QueryWrapper<Daily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated",day);
        baseMapper.delete(wrapper);
        R r = ucenterClient.countRegisterNum(day);
        Integer registerCount = (Integer) r.getData().get("result");
        //把获取数据添加到统计分析表里面
        Integer courseNum = RandomUtils.nextInt(100, 200);//TODO
        Integer videoViewNum = RandomUtils.nextInt(100, 200);
        Integer loginNum = RandomUtils.nextInt(100, 200);

        Daily daily =new Daily();
        daily.setDateCalculated(day);
        daily.setRegisterNum(registerCount);
        daily.setCourseNum(courseNum);
        daily.setLoginNum(loginNum);
        daily.setVideoViewNum(videoViewNum);

        baseMapper.insert(daily);
    }

    /**
     * 根据时间范围查询
     * @param type
     * @param begin
     * @param end
     * @return
     */
    @Override
    public Map<String, Object> getDataCount(String type, String begin, String end) {
        //构建查询条件
        QueryWrapper<Daily> queryWrapper = new QueryWrapper();
        queryWrapper.between("date_calculated",begin,end);
        //查询指定字段
        //时间和具体查询因子，前端传递type就是字段名字
        queryWrapper.select("date_calculated",type);
        List<Daily> dailyList = baseMapper.selectList(queryWrapper);
        //将list转为map
        //list中第一部分为时间:['2019-01-23','2019-09-23']
        //第二部分为数量[2,3]
        //遍历list
        //创建集合用于储存所有时间
        List<String> timeList = new ArrayList<>();
        //创建数量集合
        List<Integer> dataList = new ArrayList<>();
        for (int i = 0 ; i < dailyList.size() ; i++){
            Daily daily = dailyList.get(i);
            //获取时间
            String dateCalculated = daily.getDateCalculated();
            //放入时间集合中
            timeList.add(dateCalculated);
            //2.构建所有数据的list集合
            //从daily对象中获取数据
            //因为获取那个字段数据不一定，所以根据type进行判断，type是数据字段名称
            switch (type){
                case "login_num":
                    Integer loginNum = daily.getLoginNum();
                    dataList.add(loginNum);
                    break;
                case "register_num":
                    Integer registerNum = daily.getRegisterNum();
                    dataList.add(registerNum);
                    break;
                case "video_view_num":
                    Integer videoViewNum = daily.getVideoViewNum();
                    dataList.add(videoViewNum);
                    break;
                case "course_num":
                    Integer courseNum = daily.getCourseNum();
                    dataList.add(courseNum);
                    break;
                default:
                    break;
            }
        }
        //把构建出来的两个list集合放到map集合中
        Map<String,Object> map = new HashMap<>();
        map.put("timeList",timeList);
        map.put("dataList",dataList);
        return map;
    }
}
