package com.xmut.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.xmut"})
@EnableFeignClients
public class ServiceCmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCmsApplication.class,args);
    }
}
