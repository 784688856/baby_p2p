package com.xkkj.code.config;

import com.xkkj.common.util.IdWorker;
import com.xkkj.common.util.QQEmail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * CREATE BY YB ON 2020/1/20 14:33
 */
@Configuration
public class BeanUtils {

    /**
     * 配置到工程中
     * 雪花算法生成主键
     *
     * @return
     */
    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }

    @Bean
    public QQEmail qqEmail() {
        return new QQEmail();
    }


}
