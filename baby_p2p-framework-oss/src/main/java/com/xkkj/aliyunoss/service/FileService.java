package com.xkkj.aliyunoss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @description: FileService
 * @author: xuhao
 * @time: 2020/1/7 11:38
 */
public interface FileService {
    /**
     * 文件上传至阿里云
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
