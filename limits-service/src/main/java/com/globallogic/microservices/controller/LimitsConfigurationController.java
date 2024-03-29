package com.globallogic.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.microservices.bean.LimitConfiguration;
import com.globallogic.microservices.configuration.Configuration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class LimitsConfigurationController {

	@Autowired
	private Configuration configuration;

	@GetMapping("/limits")
	public LimitConfiguration getLimitsFromConfiguration() {
		return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());

	}

	@GetMapping("/limits-fault-tolerance-example")
	@HystrixCommand(fallbackMethod = "fallbackGetConfiguration")
	public LimitConfiguration getConfiguration() {
		throw new RuntimeException("Not Available");

	}

	public LimitConfiguration fallbackGetConfiguration() {
		return new LimitConfiguration(111, 11);

	}
}
