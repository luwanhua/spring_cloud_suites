package com.lwh.spring.cloud.nacos.consumer.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.lwh.spring.cloud.nacos.provider.feign.api","com.lwh.spring.cloud.nacos.consumer.example"})
public class NacosConsumerExampleApplication {

  @LoadBalanced
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  public static void main(String[] args) {
    SpringApplication.run(NacosConsumerExampleApplication.class, args);
  }

  @RestController
  public class TestController {

    private final RestTemplate restTemplate;

    @Autowired
    public TestController(RestTemplate restTemplate) {this.restTemplate = restTemplate;}


    /**
     * http://localhost:8040/restTemplate/invoke/port
     */
    @RequestMapping(value = "/restTemplate/invoke/port", method = RequestMethod.GET)
    public String echo() {
      return restTemplate.getForObject("http://service-provider/echo/port", String.class);
    }
  }

}
