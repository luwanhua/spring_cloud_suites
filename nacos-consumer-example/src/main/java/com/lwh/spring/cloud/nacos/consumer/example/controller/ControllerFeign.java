package com.lwh.spring.cloud.nacos.consumer.example.controller;


import com.lwh.spring.cloud.nacos.provider.feign.api.ProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ControllerFeign {


	@Autowired
	ProviderService providerService;

	/**
	 * http://localhost:8040/consumer/feign/invoke/port
	 */
	@RequestMapping(value = "/consumer/feign/invoke/port", method = RequestMethod.GET)
	public String echo(){
		log.info("进入了feign调用。。。");
		String echo = providerService.echo();
		return echo;
	}

}
