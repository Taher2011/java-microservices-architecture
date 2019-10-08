package com.globallogic.microservices.currencyexchangeservice;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

public class CurrencyLoggingFilter extends CommonsRequestLoggingFilter {

	private static final Logger LOGGER = LogManager.getLogger(CurrencyLoggingFilter.class);

	@Override
	protected boolean shouldLog(HttpServletRequest request) {
		if (true) {
			LOGGER.info("Request detail logging has been disabled for this endpoint - {}, check application properties",
					request.getAttribute("test"));
		}
		return true;
	}
	
	@Override
	protected void beforeRequest(HttpServletRequest request, String message) {
	    logger.info(message);
	}

	@Override
	protected void afterRequest(HttpServletRequest request, String message) {
	    logger.info(message);
	}
}
