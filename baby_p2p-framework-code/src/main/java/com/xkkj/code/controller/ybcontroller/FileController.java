package com.xkkj.code.controller.ybcontroller;

import com.xkkj.code.service.FileService;
import com.xkkj.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * CREATE BY YB ON 2019/12/6 11:13
 */
@Api(tags = "阿里云文件管理")
@RestController
@RequestMapping("admin/oss/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("upload")
    public Result upload(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file,

            @ApiParam(name = "host", value = "文件上传路径", required = false)
            @RequestParam(name = "host",required = false) String host
    ) {
        String uploadUrl = fileService.upload(file);
        Map map = new HashMap<String,String>()   ;
        map.put("url",uploadUrl);
        return Result.ok().result(map);
    }

}
