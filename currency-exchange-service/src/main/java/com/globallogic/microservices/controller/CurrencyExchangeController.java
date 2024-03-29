package com.globallogic.microservices.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.microservices.model.CurrencyExchangeValueResponse;
import com.globallogic.microservices.service.CurrencExchangeValueService;

@RestController
public class CurrencyExchangeController {

	@Autowired
	private CurrencExchangeValueService currencExchangeValueService;

	private static final Logger logger = LogManager.getLogger(CurrencyExchangeController.class);

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ResponseEntity<CurrencyExchangeValueResponse> retreiveCurrencyExchangeValue(
			@PathVariable(value = "from", required = true) String from,
			@PathVariable(value = "to", required = true) String to,
			@RequestHeader(value = "x-userName") String userName) {
		return new ResponseEntity<>(currencExchangeValueService.retreiveCurrencyExchangeValue(from, to, userName),
				HttpStatus.OK);
	}

}
