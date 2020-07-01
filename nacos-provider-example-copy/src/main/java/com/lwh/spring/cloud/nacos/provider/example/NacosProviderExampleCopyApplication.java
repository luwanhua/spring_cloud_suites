package com.lwh.spring.cloud.nacos.provider.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosProviderExampleCopyApplication {

  @Autowired
  ApplicationContext applicationContext;

  public static void main(String[] args) {
    SpringApplication.run(NacosProviderExampleCopyApplication.class, args);
  }

  /**
   * http://localhost:8020/echo/port
   */
  @RestController
  class EchoController {
    @RequestMapping(value = "/echo/port", method = RequestMethod.GET)
    public String echo() {
      String port = applicationContext.getEnvironment().getProperty("local.server.port");
      return "Hello Nacos Discovery " + port;
    }
  }

}
