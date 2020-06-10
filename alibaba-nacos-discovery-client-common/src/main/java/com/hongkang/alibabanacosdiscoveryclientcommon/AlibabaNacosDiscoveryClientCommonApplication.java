package com.hongkang.alibabanacosdiscoveryclientcommon;

import com.hongkang.alibabanacosdiscoveryclientcommon.service.FeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AlibabaNacosDiscoveryClientCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaNacosDiscoveryClientCommonApplication.class, args);
    }

    @RestController
    @Slf4j
    static class TestController{

        @Autowired
        private FeignService feignService;

        /*@Autowired
        LoadBalancerClient loadBalancerClient;

        @GetMapping("/test")
        public String test(){
            ServiceInstance instance = loadBalancerClient.choose("alibaba-nacos-discovery-server");
            String url=instance.getUri()+"/hello?name="+"didi";
            RestTemplate restTemplate = new RestTemplate();
            String forObject = restTemplate.getForObject(url, String.class);
            log.info(" invoke "+url+" result "+forObject);
            return forObject;
        }*/

       /* @Autowired
        private RestTemplate restTemplate;*/

        @Resource(name = "webClient")
        private WebClient.Builder webClient;


      /*  @GetMapping("/test")
        public String test(){
            return restTemplate.getForObject("http://alibaba-nacos-discovery-server/hello?name="+"张三",String.class);
        }*/

        @GetMapping("/test2")
        public Mono<String> test2(){
            return webClient.build()
                    .get()
                    .uri("http://alibaba-nacos-discovery-server/hello?name=" + "李四")
                    .retrieve()
                    .bodyToMono(String.class);
        }

        @GetMapping("/test3")
        public String test3(@RequestParam String name){
           return feignService.hello(name);
        }




      /*  @Bean
        @LoadBalanced
        public RestTemplate getRestTemplate(){
            return new RestTemplate();
        }*/


        @Bean("webClient")
        @LoadBalanced
        public WebClient.Builder getBuilder(){
            return WebClient.builder();
        }
    }

}
