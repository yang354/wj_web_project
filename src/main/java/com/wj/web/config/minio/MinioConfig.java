package com.wj.web.config.minio;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @projectName: wj_web_project
 * @package: com.wj.web.config.minio
 * @className: MinioConfig
 * @author: JaHero
 * @description: minio配置类
 * @date: 2022/9/18 18:04
 * @version: 1.0
 */
@Configuration
public class MinioConfig {
    @Value("${minio.url}")
    private String url;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;

    @Bean
    public MinioClient getMinioClient() {
        return MinioClient.builder().endpoint(url)
                .credentials(accessKey, secretKey).build();
    }
}

