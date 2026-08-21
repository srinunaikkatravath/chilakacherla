package com.chilakacherla.research;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ChilakacherlaResearchEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChilakacherlaResearchEngineApplication.class, args);
    }
}
