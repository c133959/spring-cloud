package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {

        int result = paymentService.create(payment);
        log.info("====插入结果:" + result);
        
        if (result > 0) {
            return new CommonResult(200,"插入成功,serverPort" + serverPort, result);
        } else {
            return new CommonResult(400, "插入失败,serverPort" + serverPort, null);
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

}
