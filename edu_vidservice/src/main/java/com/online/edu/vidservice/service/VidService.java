package com.online.edu.vidservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @version 1.0 2020/4/25
 * @auther LENOVO
 */
public interface VidService {
    String uploadVideoAliyun(MultipartFile file);

    void deleteAliyunVideoId(String videoId);

    void deleteMoreVideo(List<String> videoList);
}
