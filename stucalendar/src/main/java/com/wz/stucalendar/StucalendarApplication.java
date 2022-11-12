package com.wz.stucalendar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@MapperScan("com.wz.stucalendar.dao")
public class StucalendarApplication {

    public static void main(String[] args) {
        SpringApplication.run(StucalendarApplication.class, args);
    }

}
