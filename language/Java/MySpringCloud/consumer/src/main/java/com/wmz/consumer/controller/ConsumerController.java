package com.wmz.consumer.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wmz.consumer.bean.Bill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("/consumer")
@Slf4j
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "queryByIdFallback")
    public String queryById(@PathVariable("id") Long id) {
//        List<ServiceInstance> instances = discoveryClient.getInstances("account-server");
//        ServiceInstance serviceInstance = instances.get(0);
//        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() +  "/bill/" + id;
        String url = "http://account-server/bill/" + id;
        return restTemplate.getForObject(url, String.class);
    }

    public String queryByIdFallback(Long id) {
        log.error("查询用户信息失败。id:{}", id);
        return "网络很拥挤";
    }
}
