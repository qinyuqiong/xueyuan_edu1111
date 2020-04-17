package com.online.edu.eduservice.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:自定义异常处理
 * @author: yuqiong
 * @createDate: 2020/3/15
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EduException extends RuntimeException{
    private Integer code;//状态码
    private String message;//异常信息
}
