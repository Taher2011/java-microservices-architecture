package com.globallogic.microservices.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.globallogic.microservices.model.CurrencyConversionResponse;

@Service
public class CurrencyConversionService {

	@Autowired
	private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

	public CurrencyConversionResponse convertCurrency(String from, String to, BigDecimal quantity, String userName) {

		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		HttpHeaders headers = createHttpHeaders(userName);
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		String url = "http://localhost:8000/currency-exchange/from/{from}/to/{to}";

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<CurrencyConversionResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity,
				CurrencyConversionResponse.class, uriVariables);

		CurrencyConversionResponse currencyConversionResponse = responseEntity.getBody();
		currencyConversionResponse.setQuantity(quantity);
		currencyConversionResponse.setTotalCalculatedAmount(
				currencyConversionResponse.getQuantity().multiply(currencyConversionResponse.getConversionMultiple()));
		return currencyConversionResponse;

	}

	/**
	 * Method is used to invoke external microservice using feign client i.e bioler
	 * plate code is not required like rest template
	 * 
	 * @param from
	 * @param to
	 * @param quantity
	 * @param userName
	 * @return
	 */
	public CurrencyConversionResponse convertCurrencyFeign(String from, String to, BigDecimal quantity,
			String userName) {

		CurrencyConversionResponse currencyConversionResponse = currencyExchangeServiceProxy
				.retreiveCurrencyExchangeValue(userName, from, to);
		currencyConversionResponse.setQuantity(quantity);
		currencyConversionResponse.setTotalCalculatedAmount(
				currencyConversionResponse.getQuantity().multiply(currencyConversionResponse.getConversionMultiple()));
		return currencyConversionResponse;

	}

	private HttpHeaders createHttpHeaders(String userName) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("x-userName", userName);
		return headers;
	}

}
