package com.hongkang.alibabanacosconfigclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
public class AlibabaNacosConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaNacosConfigClientApplication.class, args);
    }

}

@RestController
@Slf4j
@RefreshScope
class TestController{

    @Value("${sina.name}")
    String title;

    @GetMapping("/hello")
    public String hello(){
        return title;
    }

}
