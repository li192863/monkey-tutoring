package com.chestnut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 内容管理服务启动类
 *
 * @author: Chestnut
 * @since: 2023-08-16
 **/
@SpringBootApplication
@EnableSwagger2
public class ContentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContentApplication.class, args);
    }
}
