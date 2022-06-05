package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定的轮询配置类不能放在@ComponentScan所扫描的包以及其子包下，
 * 否则我们自定的这个配置类就会被所有的Ribbon客户端共享，达不到定制化的目的
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule () {
        return new RandomRule();//定义为随机
    }

}
