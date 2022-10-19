package com.wj.web;



import com.wj.web.config.redis.RedisService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.wj")
public class AdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(com.wj.web.AdminApplication.class, args);
	}
}