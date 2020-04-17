package com.online.edu.xueyuan_common;

/**
 * 定义操作状态码
 * @description:
 * @author: yuqiong
 * @createDate: 2020/3/14
 * @version: 1.0
 */
public interface ResultCode {
    int SUCCESS = 20000;//成功状态码
    int ERROR = 20001;//失败状态码
    int AUTH  = 30000;//没有操作权限状态码
}
