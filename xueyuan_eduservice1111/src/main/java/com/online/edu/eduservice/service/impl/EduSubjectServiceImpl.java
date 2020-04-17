package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.eduservice.entity.EduSubject;
import com.online.edu.eduservice.entity.dto.OneSubjectDto;
import com.online.edu.eduservice.entity.dto.TwoSubjectDto;
import com.online.edu.eduservice.handler.EduException;
import com.online.edu.eduservice.mapper.EduSubjectMapper;
import com.online.edu.eduservice.service.EduSubjectService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-04-07
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {


    //读取excel的内容
    @Override
    public List<String> importSubject(MultipartFile file) {
        try {
            //1.获取文件输入流
            InputStream inputStream = file.getInputStream();
            //2.创建workbook
            Workbook workbook = new HSSFWorkbook(inputStream);
            //3.workbook获取sheet
            Sheet sheet = workbook.getSheetAt(0);
            //为了存储错误信息
            List<String> msg = new ArrayList<>();
            //4.sheet获取row
            //从第二行开始获取数据，获取最后一行的数字
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                //得到excel每一行
                Row row = sheet.getRow(i);
                if (row == null) {
                    String str = "表格数据为空，请输入数据";
                    //TODO
                    msg.add(str);
                    return msg;
                }
                //行数据不为空
                //5.1 row获取cell
                Cell cellOne = row.getCell(0);
                //判断列是否为空
                if (cellOne == null) {
                    String str = i+"列数据为空，请输入数据";
                    //跳出这一行，往下继续执行
                    msg.add(str);
                    continue;
                }
                //列不为空获取数据，第一列值
                String cellOneValue = cellOne.getStringCellValue();
                //添加一级分类之前判断，判断要添加的一级分类在数据库表中是否存在，如果不存在就添加
                EduSubject eduSubjectExist = this.existOneUbject(cellOneValue);
                //存储一级id
                String id_parent = null;
                if (eduSubjectExist == null) {
                    EduSubject eduSubject = new EduSubject();
                    eduSubject.setTitle(cellOneValue);
                    eduSubject.setParentId("0");
                    eduSubject.setSort(0);
                    baseMapper.insert(eduSubject);
                    id_parent = eduSubject.getId();
                } else {//存在，不添加
                    id_parent = eduSubjectExist.getId();
                }


                //5.2 row获取第二列
                Cell cellTwo = row.getCell(1);
                if (cellTwo == null) {
                    String str = i+"列数据为空，请输入数据";
                    //跳出这一行，往下继续执行
                    msg.add(str);
                    continue;
                }
                //不为空，获取数据，第二列的值
                String cellTwoValue = cellTwo.getStringCellValue();
                //添加一级分类和二级分类，体现出层级关系
                EduSubject TwoexistUbject = this.existTwoUbject(cellTwoValue, id_parent);
                if (TwoexistUbject == null) {
                    EduSubject twoSubject = new EduSubject();
                    twoSubject.setTitle(cellTwoValue);
                    twoSubject.setParentId(id_parent);
                    twoSubject.setSort(0);
                    baseMapper.insert(twoSubject);
                } else {

                }
            }

            //6.cell获取cell值

            //7.获取到道德值添加到数据库
            return msg;
        } catch (Exception e) {
            e.printStackTrace();
            throw new EduException(20001,"导入失败出现异常");
        }

    }


    //删除分类
    @Override
    public boolean deleteSubjectById(String id) {
        //判断一级分类下面有二级分类
        //根据parent_id查询
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        Integer count = baseMapper.selectCount(wrapper);
        //判断如果有二级分类
        if (count>0) {
            return false;
        } else {//没有二级分类
            //进行删除
            int result = baseMapper.deleteById(id);
            return result>0;
        }
    }

    //添加一级分类
    @Override
    public boolean saveOneLevel(EduSubject eduSubject) {
        //判断是否已有
        EduSubject eduSubjectExist = this.existOneUbject(eduSubject.getTitle());
        if (eduSubjectExist == null){
            //添加
            //一级分类parentid=0
            eduSubject.setParentId("0");
            int result = baseMapper.insert(eduSubject);
            return result > 0;
        }else {
            return false;
        }
    }

    //添加二级分类
    @Override
    public boolean seveTwoLevel(EduSubject eduSubject) {
        EduSubject eduSubjectExist = this.existTwoUbject(eduSubject.getTitle(), eduSubject.getParentId());
        if (eduSubjectExist == null){
            //添加
            int result = baseMapper.insert(eduSubject);
            return result > 0;
        }else {
            return false;
        }
    }

    // 返回数据
    @Override
    public List<OneSubjectDto> getSubjectList() {
        //1 查询所有一级分类
        QueryWrapper<EduSubject> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("parent_id","0");
        List<EduSubject> allOneSubjects = baseMapper.selectList(wrapper1);

        //2 查询所有二级分类
        QueryWrapper<EduSubject> wrapper2 = new QueryWrapper<>();
        wrapper2.ne("parent_id","0");
        List<EduSubject> allTwoSubjects = baseMapper.selectList(wrapper2);

        //创建list集合，用于存储所有一级分类
        List<OneSubjectDto> oneSubjectDtolist = new ArrayList<>();
        //3 首先构建一级分类
        //遍历所有的一级分类，得到每个EduSubject对象，把每个EduSubject对象转换OneSubjectDto
        for (int i = 0; i < allOneSubjects.size(); i++) {
            //获取每个EduSubject对象
            EduSubject eduOneSubject = allOneSubjects.get(i);
            //创建OneSubjectDto对象
            OneSubjectDto oneSubjectDto = new OneSubjectDto();
            //把每个EduSubject对象转换OneSubjectDto
            BeanUtils.copyProperties(eduOneSubject,oneSubjectDto);
            //把dto对象放到list集合
            oneSubjectDtolist.add(oneSubjectDto);

            //获取一级分类所有二级分类，List<TwoSubjectDto>
            //把所有的二级分类添加到每个一级分类对象中oneSubjectDto.setChildren(list);
            //创建list集合，用于存储二级分类
            List<TwoSubjectDto>  twoSubjectDtoList = new ArrayList<>();
            //遍历所有的二级分类，得到每个二级分类
            for (int m = 0; m < allTwoSubjects.size(); m++) {
                //得到每个二级分类
                EduSubject eduTwoSubject = allTwoSubjects.get(m);
                //判断一级分类id和二级分类parentid是否一样
                if(eduTwoSubject.getParentId().equals(eduOneSubject.getId())) {
                    //二级分类转换TwoSubjectDto
                    TwoSubjectDto twoSubjectDto = new TwoSubjectDto();
                    //内省  反射
                    BeanUtils.copyProperties(eduTwoSubject,twoSubjectDto);
                    //放到list集合
                    twoSubjectDtoList.add(twoSubjectDto);
                }
            }
            //把二级分类放到每个一级分类中
            oneSubjectDto.setChildren(twoSubjectDtoList);
        }
        return oneSubjectDtolist;
    }

    //判断数据库是否存在二级分类
    private EduSubject existTwoUbject(String name , String parentid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        //拼接条件
        wrapper.eq("title",name);
        wrapper.eq("parent_id",parentid);
        //调用方法
        EduSubject eduSubject = baseMapper.selectOne(wrapper);
        return eduSubject;
    }

    //判断数据库是否存在一级分类
    private EduSubject existOneUbject(String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        //拼接条件
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        //调用方法
        EduSubject eduSubject = baseMapper.selectOne(wrapper);
        return eduSubject;
    }
}
