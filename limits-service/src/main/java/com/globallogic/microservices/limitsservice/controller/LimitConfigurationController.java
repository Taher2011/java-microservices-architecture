package com.globallogic.microservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.microservices.limitsservice.bean.LimitConfiguration;
import com.globallogic.microservices.limitsservice.configuration.Configuration;

@RestController
public class LimitConfigurationController {

	@Autowired
	private Configuration configuration;

	@GetMapping("/limits")
	public LimitConfiguration getLimitsFromConfiguration() {
		return new LimitConfiguration(configuration.getMinimum(), configuration.getMaximum());

	}

}
