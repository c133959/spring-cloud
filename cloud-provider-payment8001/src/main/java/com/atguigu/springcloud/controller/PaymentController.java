package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    private static final String SERVICE_INSTANCE = "cloud-payment-service";

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {

        int result = paymentService.create(payment);
        log.info("====插入结果:" + result);
        
        if (result > 0) {
            return new CommonResult(200,"插入成功,serverPort:" + serverPort, result);
        } else {
            return new CommonResult(400, "插入失败,serverPort:" + serverPort, null);
        }
        
    }

    //往数据库写数据-POSTMapping，读数据-GetMapping
    @GetMapping("/payment/get/{id}")
    public CommonResult getItemsById(@PathVariable("id") Long id) {
        Payment paymentById = paymentService.getPaymentById(id);
        log.info("====查询结果："+ paymentById);

        if (paymentById != null) {
            return new CommonResult(200,"查询成功,serverPort" + serverPort, paymentById);
        } else {
             return new CommonResult(400,"查询失败,id:" + id,null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {// 获取服务-列表
            log.info("*****service" + service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances(SERVICE_INSTANCE);// 通过服务ID获取服务实例
        for (ServiceInstance instance : instances) {
            log.info("*****instance" +
                    instance.getServiceId() + "\t" +
                    instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());// 一个微服务名称下的所有实例
        }
        return this.discoveryClient;
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

}
