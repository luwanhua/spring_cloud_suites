package com.lwh.spring.cloud.nacos.config.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

	@Value("${useLocalCache:false}")
	private boolean useLocalCache;

	@Value("${userName}")
	private String userName;

	@Value("${age}")
	private String age;

	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * http://localhost:8010/config/get
	 */
	@RequestMapping("/get")
	public boolean get() {
		return useLocalCache;
	}

	/**
	 * http://localhost:8010/config/get/dynamic
	 */
	@RequestMapping("/get/dynamic")
	public String getDynamic() throws InterruptedException {

		LocalDateTime start = LocalDateTime.now();

		while(true) {
			userName = applicationContext.getEnvironment().getProperty("userName");
			age = applicationContext.getEnvironment().getProperty("age");
			System.err.println("user name :" + userName + "; age: " + age);
			TimeUnit.SECONDS.sleep(1);
			LocalDateTime now = LocalDateTime.now();
			if(now.isAfter(start.plusMinutes(1))){
				break;
			}
		}
		return "hello";

	}
}
