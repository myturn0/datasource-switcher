package com.apollo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ：apollo
 * @since ：Created in 2019/2/23
 */
@SpringBootApplication
@MapperScan("com.apollo.dao")
public class DsSwitchApplication {
    public static void main(String[] args) {
        SpringApplication.run(DsSwitchApplication.class, args);
    }
}
