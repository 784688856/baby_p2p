package com.xkkj.code.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.xkkj.code.config.ConstantPropertiesUtil;
import com.xkkj.code.service.FileService;
import com.xkkj.common.constants.ResultCodeEnum;
import com.xkkj.common.exception.BabyP2pException;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * CREATE BY YB ON 2019/12/6 10:43
 */
@Service
public class FileServiceImpl implements FileService {
    @Override
    public String upload(MultipartFile file) {
        String endPoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        String fileHost = ConstantPropertiesUtil.FILE_HOST;

        String uploadUrl = null;

        try {
            OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
            if (!ossClient.doesBucketExist(bucketName)) {
                // 创建bucket
                ossClient.createBucket(bucketName);
                // 设置实例的访问权限：公共读
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }
            // 获取上传文件流
            InputStream inputStream = file.getInputStream();
            // 构建日期路径 avatar/2019/11/19/文件名
            String filePath = new DateTime().toString("yyyy/MM/dd");
            // 文件名：uuid.扩展名
            String originalFilename = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString();
            // 文件类型
            String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 创建新名字
            String newName = fileName + fileType;
            // 设置访问文件的云地址(后半部分)
            String fileUrl = fileHost + "/" + filePath + "/" + newName;
            // 存入阿里云，只需要获取上传流就可以传入oss中了
            // 自己写的fds文件上传需要将用户上传的file先转为本地的file，
            // 然后通过获取新文件本地绝对路径找到新文件来上传
            ossClient.putObject(bucketName, fileUrl, inputStream);
            // 关闭ossClient
            ossClient.shutdown();
            uploadUrl = "http://" + bucketName + "." + endPoint + "/" + fileUrl;
            System.out.println("uploadUrl--->"+uploadUrl);
            return uploadUrl;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BabyP2pException(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }
    }
}
