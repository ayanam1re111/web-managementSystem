package com.ayanami.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data//提供get/set
@Component//给IOC属性管理
@ConfigurationProperties(prefix = "aliyun.oss")//制定要封装的是配置文件哪一个前缀下的值
public class AliyunOSSProperties {
    private String endpoint;
    private String bucketName;
    private String region;
}