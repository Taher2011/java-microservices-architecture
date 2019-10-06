package com.globallogic.microservices.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.microservices.model.CurrencyConversionResponse;
import com.globallogic.microservices.service.CurrencyConversionService;

@RestController
public class CurrencyConversionController {

	@Autowired
	private CurrencyConversionService currencyConversionService;

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public ResponseEntity<CurrencyConversionResponse> convertCurrency(
			@PathVariable(value = "from", required = true) String from,
			@PathVariable(value = "to", required = true) String to,
			@PathVariable(value = "quantity", required = true) BigDecimal quantity,
			@RequestHeader(value = "x-userName") String userName) {
		return new ResponseEntity<>(currencyConversionService.convertCurrency(from, to, quantity, userName),
				HttpStatus.OK);
	}

}
