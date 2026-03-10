package com.ayanami.controller;

import com.ayanami.pojo.Result;
import com.ayanami.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    /**
     * 上传文件 - 参数名file
     */

//    public Result upload(String username, Integer age , MultipartFile file) throws Exception {
//        log.info("上传文件：{}, {}, {}", username, age, file);
//        //上传的时候并没有将文件保存到本地
//        if (!file.isEmpty()) {
//            // 生成唯一文件名
//            String originalFilename = file.getOriginalFilename();
//            String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
//            String uniqueFileName = UUID.randomUUID().toString().replace("-", "") + extName;
//            // 拼接完整的文件路径
//            File targetFile = new File("D://images/" + uniqueFileName);
//
//            // 如果目标目录不存在，则创建它
//            if (!targetFile.getParentFile().exists()) {
//                targetFile.getParentFile().mkdirs();
//            }
//            // 保存文件
//            file.transferTo(targetFile);
//        }
//        return Result.success();
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;
    @PostMapping("/upload")
    public Result upLoad(MultipartFile file) throws Exception {
        log.info("文件上传：{}", file.getOriginalFilename());
        //将文件交给OSS存储管理(upload方法有返回值：URL）
        String url=aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("URL:{}",url);
        return Result.success(url);
    }
    }

