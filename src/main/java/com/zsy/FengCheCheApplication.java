package com.zsy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.gitee.sunchenbin.mybatis.actable.manager.*", "com.zsy.*"})
@MapperScan(basePackages = {"com.gitee.sunchenbin.mybatis.actable.dao.*", "com.zsy.moudle.*.mapper"})
public class FengCheCheApplication {
    public static void main(String[] args) {
        SpringApplication.run(FengCheCheApplication.class, args);
    }
}
