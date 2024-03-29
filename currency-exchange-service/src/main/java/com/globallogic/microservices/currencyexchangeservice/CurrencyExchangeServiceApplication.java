package com.globallogic.microservices.currencyexchangeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import brave.sampler.Sampler;

@SpringBootApplication
@ComponentScan(basePackages = "com.globallogic.microservices")
@EnableJpaRepositories(basePackages = "com.globallogic.microservices")
@EntityScan("com.globallogic.microservices.entity")
@EnableDiscoveryClient()
public class CurrencyExchangeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeServiceApplication.class, args);
	}

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}

	@Bean
	public CurrencyLoggingFilter filter() {
		CurrencyLoggingFilter currencyLoggingFilter = new CurrencyLoggingFilter();
		currencyLoggingFilter.setIncludeQueryString(true);
		currencyLoggingFilter.setIncludePayload(true);
		currencyLoggingFilter.setMaxPayloadLength(20000);
		currencyLoggingFilter.setIncludeHeaders(true);
		currencyLoggingFilter.setAfterMessagePrefix("REQUEST DATA : ");
		return currencyLoggingFilter;
	}

}
