package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@EnableTransactionManagement
public class ReggierTakeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReggierTakeApplication.class, args);
        log.info("项目启动成功");
    }

}
