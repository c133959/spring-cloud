package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
// 该注解用于使用consul或者zookeeper座额我i注册中心的注册服务
@EnableDiscoveryClient
//不使用Eureka时，可以不用加EurekaServer、EurekaClient注解
public class PaymentMain8004 {

    public static void main(String[] args) {

        SpringApplication.run(PaymentMain8004.class, args);

    }

}
