package com.zhiyan.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@MapperScan("com.zhiyan.user.dao")
@EntityScan("com.zhiyan.model.user")//扫描实体类
@ComponentScan(basePackages={"com.zhiyan.api"})//扫描接口
@ComponentScan(basePackages={"com.zhiyan.common"})//扫描common包
@SpringBootApplication
public class UserApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(UserApplication.class, args);
    }

}