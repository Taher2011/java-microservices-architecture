package com.globallogic.microservices.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.globallogic.microservices.constant.ErrorMessage;
import com.globallogic.microservices.controller.exception.AuthenticationException;
import com.globallogic.microservices.controller.exception.NotFoundException;
import com.globallogic.microservices.entity.CurrencyExchangeValue;
import com.globallogic.microservices.model.CurrencyExchangeValueResponse;
import com.globallogic.microservices.repository.CurrencExchangeValueRepository;

@Service
public class CurrencExchangeValueService {

	@Autowired
	private CurrencExchangeValueRepository currencExchangeValueRepository;

	@Autowired
	private Environment environment;

	private static final Logger logger = LogManager.getLogger(CurrencExchangeValueService.class);

	public CurrencyExchangeValueResponse retreiveCurrencyExchangeValue(String from, String to, String userName) {

		CurrencyExchangeValueResponse exchangeValueResponse = null;

		if (currencExchangeValueRepository.findByCreatedBy(userName).isPresent()) {
			Optional<CurrencyExchangeValue> exchangeValue = currencExchangeValueRepository.findByFromAndTo(from, to);
			if (exchangeValue.isPresent()) {
				exchangeValueResponse = new CurrencyExchangeValueResponse();
				prepareCurrencyExchangeValueResponse(exchangeValueResponse, exchangeValue.get());

				logger.info("{}", exchangeValueResponse);

			} else {
				throw new NotFoundException(
						ErrorMessage.ERR_13_MESSAGE.getMessage().replace("${object}", "Currency Conversion"));
			}
		} else {
			throw new AuthenticationException(
					ErrorMessage.ERR_12_MESSAGE.getMessage().replace("${object} ", "UserName " + userName + " "));
		}

		return exchangeValueResponse;
	}

	private CurrencyExchangeValueResponse prepareCurrencyExchangeValueResponse(
			CurrencyExchangeValueResponse currencyExchangeValueResponse, CurrencyExchangeValue currencyExchangeValue) {
		currencyExchangeValueResponse.setId(currencyExchangeValue.getId());
		currencyExchangeValueResponse.setFrom(currencyExchangeValue.getFrom());
		currencyExchangeValueResponse.setTo(currencyExchangeValue.getTo());
		currencyExchangeValueResponse.setConversionMultiple(currencyExchangeValue.getConversionMultiple());
		currencyExchangeValueResponse.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return currencyExchangeValueResponse;
	}

}
