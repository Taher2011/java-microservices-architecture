package com.globallogic.microservices.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globallogic.microservices.entity.ExchangeValue;

@Repository
public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {

	Optional<ExchangeValue> findByCreatedBy(String userName);

	Optional<ExchangeValue> findByFromAndTo(String from, String to);

}
