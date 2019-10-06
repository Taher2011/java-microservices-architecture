package com.globallogic.microservices.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globallogic.microservices.entity.CurrencyExchangeValue;

@Repository
public interface CurrencExchangeValueRepository extends JpaRepository<CurrencyExchangeValue, Long> {

	Optional<CurrencyExchangeValue> findByCreatedBy(String userName);

	Optional<CurrencyExchangeValue> findByFromAndTo(String from, String to);

}
