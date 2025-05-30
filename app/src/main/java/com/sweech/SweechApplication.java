package com.sweech;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sweech.repository") 
public class SweechApplication {
    public static void main(String[] args) {
        SpringApplication.run(SweechApplication.class, args);
        System.out.println("Sweech Application started successfully!");
    }
}
