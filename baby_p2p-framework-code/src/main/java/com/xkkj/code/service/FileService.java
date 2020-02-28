package com.xkkj.code.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * CREATE BY YB ON 2019/12/6 10:38
 */
@Component
public interface FileService {
    /**
     * 文件上传到阿里云服务器
     *
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
