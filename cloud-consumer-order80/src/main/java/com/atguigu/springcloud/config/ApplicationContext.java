package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: ApplicationContext
 * @Description: TODO
 * @Author sunsl
 * @Date 2022/4/14 21:49
 * @Version 1.0
 */
@Configuration
public class ApplicationContext {

    @Bean
//    @LoadBalanced 注释掉，使用我们自定义的负载方式
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
