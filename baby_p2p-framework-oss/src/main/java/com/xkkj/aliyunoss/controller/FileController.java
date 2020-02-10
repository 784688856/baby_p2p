package com.xkkj.aliyunoss.controller;

import com.xkkj.aliyunoss.service.FileService;
import com.xkkj.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: FileController
 * @author: xuhao
 * @time: 2019/12/6 14:00
 */
@Api(tags = "阿里云OSS文件管理")
@RestController
@RequestMapping("/oss/file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     *
     * @param file
     */
    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public Result upload(@ApiParam(name = "file", value = "文件", required = true)
                        @RequestParam("file") MultipartFile file){
        String upload = fileService.upload(file);
        System.out.println("图片地址-----》"+upload);
        return Result.ok().result(upload).success(true);
    }
}
