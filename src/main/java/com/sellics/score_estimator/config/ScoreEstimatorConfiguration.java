package com.sellics.score_estimator.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ScoreEstimatorConfiguration {

	private static final String LOG_KEY = "ScoreEstimator";

	@Bean
	public Logger getLogger() {
		return LoggerFactory.getLogger(LOG_KEY);
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
