package com.xkkj.safe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * CREATE BY YB ON 2020/2/5 16:11
 */
@SpringBootApplication
@ComponentScan(basePackages={"com.xkkj.safe","com.xkkj.common"})
public class SafeApplication {
    public static void main(String[] args) {
        SpringApplication.run(SafeApplication.class,args);
    }
}
