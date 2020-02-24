package com.xkkj.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @description: EureKaApplication
 * @author: xuhao
 * @time: 2020/1/6 10:31
 */
@SpringBootApplication
@EnableEurekaServer
public class EureKaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EureKaApplication.class,args);
    }
}
