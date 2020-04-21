package com.online.edu.eduservice.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.online.edu.eduservice.handler.ConstantPropertiesUtil;
import com.online.edu.xueyuan_common.R;
import org.joda.time.DateTime;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @description:文件上传oss
 * @author: yuqiong
 * @createDate: 2020/4/4
 * @version: 1.0
 */
@RestController
@RequestMapping("/eduservice/oss")
@CrossOrigin
public class FileUploadController {
    //上传讲师头像的方法
    @PostMapping("upload")
    public R uploadTeacherImg(@RequestParam("file") MultipartFile file ,@RequestParam(value = "host",required = false) String host){

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtil.ENDPOINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtil.KEYID;
        String accessKeySecret = ConstantPropertiesUtil.KEYSECRET;
        String yourBucketName = ConstantPropertiesUtil.BUCKETNAME;
        String hostName = ConstantPropertiesUtil.HOST;

    try {
        //1.获取上传文件MultipartFile file
        //2.获取上传文件的名称，获取上传文件输入流
        String filename = file.getOriginalFilename();
        //在文件名之前添加uuid值，获取上传文件输入流,相同文件使用不同名称进行区分
        String uuid = UUID.randomUUID().toString();
        filename = uuid + filename;

        //获取当前日期并将2019.04.03==》 2019/04/13,
        String filePath = new DateTime().toString("yyyy/MM/dd");

        //拼接文件完整名称
        //2019/04/03/sdfsd01.txt
        //如果上传头像 host 里面为空，如果上传封面host有值
        //如果为空变量的值不变，存入career.有值就存入值中的指的文件夹

        if (!StringUtils.isEmpty(host)){
            hostName = host;
        }

        filename = filePath+"/"+hostName+"/"+filename;

        InputStream inputStream = file.getInputStream();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流。
        ossClient.putObject(yourBucketName, filename, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();

        //返回上传之后oss存储路径
        //https://xueyuan-edudome111.oss-cn-hangzhou.aliyuncs.com/2019/1/4/host/1.txt
        String path = "https://"+yourBucketName+"."+endpoint+"/"+filename;
        return R.ok().data("imgurl",path);

    } catch (Exception e){
        e.printStackTrace();
        return R.error();
    }

    }
}
