package com.wmz.consumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerApplicationTest {
    @Autowired
    private RibbonLoadBalancerClient client;

    @Test
    public void applicationTest(){
        for (int i = 0; i < 40; i++) {
            ServiceInstance instance = client.choose("account-server");
            System.out.println(instance.getHost()+"   " + instance.getPort());
        }
    }

}