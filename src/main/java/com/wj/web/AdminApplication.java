package com.wj.web;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.wj")
public class AdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(com.wj.web.AdminApplication.class, args);
	}
}