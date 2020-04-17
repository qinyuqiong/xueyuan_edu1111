package com.online.edu.xueyuan_common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义具体的数据返回格式
 * @description:
 * @author: yuqiong
 * @createDate: 2020/3/14
 * @version: 1.0
 */
@Data
public class R {

    private Integer code;

    private Boolean success;

    private  String message;

    private Map<String,Object> data = new HashMap<>();

    private R(){}

    /**
     * 操作成功，调用此方法，返回成功的数据
     * @return
     */
    public static R ok(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("操作成功");
        return r;
    }

    /**
     * 操作失败，调用此方法，返回失败的数据
     * @return
     */
    public static R error(){
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("操作失败");
        return r;
    }

    /**
     * 以下方法是为了链式编程
     * @param success
     * @return
     */
    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
