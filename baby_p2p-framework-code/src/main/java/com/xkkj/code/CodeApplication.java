package com.xkkj.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * CREATE BY YB ON 2020/1/6 16:11
 */
@SpringBootApplication
@ComponentScan(basePackages={"com.xkkj.code","com.xkkj.common"})
public class CodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(CodeApplication.class,args);
    }
}
