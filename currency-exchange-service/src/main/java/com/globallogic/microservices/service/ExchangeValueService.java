package com.globallogic.microservices.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.globallogic.microservices.controller.exception.AuthenticationException;
import com.globallogic.microservices.controller.exception.NotFoundException;
import com.globallogic.microservices.entity.ExchangeValue;
import com.globallogic.microservices.model.ExchangeValueResponse;
import com.globallogic.microservices.repository.ExchangeValueRepository;

@Service
public class ExchangeValueService {

	@Autowired
	private ExchangeValueRepository exchangeValueRepository;

	@Autowired
	private Environment environment;

	private static final Logger logger = LogManager.getLogger(ExchangeValueService.class);

	public ExchangeValueResponse retreiveExchangeValue(String from, String to, String userName) {

		ExchangeValueResponse exchangeValueResponse = null;

		if (exchangeValueRepository.findByCreatedBy(userName).isPresent()) {
			Optional<ExchangeValue> exchangeValue = exchangeValueRepository.findByFromAndTo(from, to);
			if (exchangeValue.isPresent()) {
				exchangeValueResponse = new ExchangeValueResponse();
				prepareExchangeValueResponse(exchangeValueResponse, exchangeValue.get());
			} else {
				throw new NotFoundException("No currency conversion found");
			}
		} else {
			throw new AuthenticationException("User Name does not exist");
		}

		return exchangeValueResponse;
	}

	private ExchangeValueResponse prepareExchangeValueResponse(ExchangeValueResponse exchangeValueResponse,
			ExchangeValue exchangeValue) {
		exchangeValueResponse.setId(exchangeValue.getId());
		exchangeValueResponse.setFrom(exchangeValue.getFrom());
		exchangeValueResponse.setTo(exchangeValue.getTo());
		exchangeValueResponse.setConversionMultiple(exchangeValue.getConversionMultiple());
		exchangeValueResponse.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return exchangeValueResponse;
	}

}
