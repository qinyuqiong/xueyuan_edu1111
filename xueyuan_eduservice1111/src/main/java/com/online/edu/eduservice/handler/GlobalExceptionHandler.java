package com.online.edu.eduservice.handler;

import com.online.edu.xueyuan_common.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.NestedServletException;

/**
 * @description:统一异常处理
 * @author: yuqiong
 * @createDate: 2020/3/15
 * @version: 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 对所有异常进行相同处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("出现异常");
    }

    /**
     * 对特定异常进行处理
     * @param e
     * @return
     */
    @ExceptionHandler(NestedServletException.class)
    @ResponseBody
    public R error(NestedServletException e){
        e.printStackTrace();
        return R.error().message("数据嵌套出现异常");
    }

    /**
     * 对自定义异常进行处理
     * @param e
     * @return
     */
    @ExceptionHandler(EduException.class)
    @ResponseBody
    public R error(EduException e){
        e.printStackTrace();
        return R.error().message(e.getMessage()).code(e.getCode());
    }
}
