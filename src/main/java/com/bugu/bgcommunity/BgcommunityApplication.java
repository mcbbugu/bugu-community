package com.bugu.bgcommunity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//扫描的mapper
@MapperScan("com.bugu.bgcommunity.mapper")
@SpringBootApplication
public class BgcommunityApplication {
    public static void main(String[] args) {
        SpringApplication.run(BgcommunityApplication.class, args);
    }
}