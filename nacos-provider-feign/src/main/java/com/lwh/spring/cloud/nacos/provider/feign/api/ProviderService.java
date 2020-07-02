package com.lwh.spring.cloud.nacos.provider.feign.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "service-provider", fallback = ProviderServiceFallback.class)
public interface ProviderService {

	@RequestMapping(value = "/provider/echo/port", method = RequestMethod.GET)
	String echo();

}
