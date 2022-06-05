package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value ="CLOUD-PAYMENT-SERVICE")//在注册中心寻找该微服务，并调用/payment/get/{id}完成服务端的接口调用
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")// 当controller层调用到此方法时，会根据/payment/get/{id}去服务端找到对应接口
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);// 与服务端的方法一致即可

}
