package com.lwh.spring.cloud.nacos.provider.feign.api;

import org.springframework.stereotype.Component;

@Component
public class ProviderServiceFallback implements ProviderService{
	@Override
	public String echo() {
		return "服务降级。。。";
	}
}
