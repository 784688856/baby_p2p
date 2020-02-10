package com.xkkj.aliyunoss.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.xkkj.aliyunoss.service.FileService;
import com.xkkj.aliyunoss.util.ConstantPropertiesUtil;
import com.xkkj.common.constants.ResultCodeEnum;
import com.xkkj.common.exception.YanXueException;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @description: FileServiceImpl
 * @author: xuhao
 * @time: 2020/1/7 14:38
 */
@Service
public class FileServiceImpl implements FileService {
    @Override
    public String upload(MultipartFile file) {
        //获取阿里云存储相关常量
        //地址
        String endPoint = ConstantPropertiesUtil.END_POINT;
        //key键
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        //keyValue值
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        //创建的Bucker仓库名称
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        //创建的上传目录
        String fileHost = ConstantPropertiesUtil.FILE_HOST;
        //上传地址
        String uploadUrl = null;
        try {
            //判断oss实例是否存在：如果不存在则创建，如果存在则获取
            OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
            if (!ossClient.doesBucketExist(bucketName)) {
                //创建bucket
                ossClient.createBucket(bucketName);
                //设置oss实例的访问权限：公共读
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }
            //获取上传文件流
            InputStream inputStream = file.getInputStream();
            //构建日期路径
            String filePath = new DateTime().toString("yyyy/MM/dd");
            //文件名：uuid.扩展名
            //文件文件名称
            String original = file.getOriginalFilename();
            //uuid名称
            String fileName = UUID.randomUUID().toString();
            //通过文件名称获取文件后缀
            String fileType = original.substring(original.lastIndexOf("."));
            //生成新的文件名称
            String newName = fileName + fileType;
            //上传存放的路径
            String fileUrl = fileHost + "/" + filePath + "/" + newName;
            //文件上传至阿里云
            ossClient.putObject(bucketName, fileUrl, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            //获取url地址
            uploadUrl = "http://" + bucketName + "." + endPoint + "/" + fileUrl;
        } catch (IOException e) {
            throw new YanXueException(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }
        //返回文件请求地址
        return uploadUrl;
    }
}
