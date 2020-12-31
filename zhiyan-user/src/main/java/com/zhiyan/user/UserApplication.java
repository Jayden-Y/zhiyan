package com.zhiyan.user;


import com.zhiyan.common.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;


@MapperScan("com.zhiyan.user.dao")
@EntityScan("com.zhiyan.model.user")//扫描实体类
@ComponentScan(basePackages={"com.zhiyan.api"})//扫描接口
@ComponentScan(basePackages={"com.zhiyan.common"})//扫描common包
@SpringBootApplication
public class UserApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(UserApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }
}