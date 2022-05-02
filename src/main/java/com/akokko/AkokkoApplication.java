package com.akokko;

import com.akokko.util.EmailUtil;
import com.akokko.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = "com.akokko.dao")
public class AkokkoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AkokkoApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1,1);
    }

    @Bean
    public EmailUtil emailUtil() {
        return new EmailUtil();
    }

}
