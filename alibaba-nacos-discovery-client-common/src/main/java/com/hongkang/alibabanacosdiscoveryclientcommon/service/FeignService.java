package com.hongkang.alibabanacosdiscoveryclientcommon.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author djl
 * @date 2020/4/28 15:50
 */

@FeignClient("alibaba-nacos-discovery-server")
public interface FeignService {
    @GetMapping("/hello")
    public String hello(@RequestParam String name);
}
