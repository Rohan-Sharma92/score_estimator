package com.sellics.score_estimator.core;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AmazonMatchingWordRetriever implements IMatchingWordRetriever {

	private final RestTemplate restTemplate;
	private final String uri;
	private static final String ALIAS = "aps";
	private final ObjectMapper mapper;
	private final Logger logger;

	@Autowired
	public AmazonMatchingWordRetriever(@Value("${amazon.auto_complete.endpoint}") final String uri,
			final Logger logger,final RestTemplate restTemplate) {
		this.logger = logger;
		this.restTemplate = restTemplate;
		this.uri = uri;
		this.mapper = new ObjectMapper();
	}

	@Override
	public List<String> retrieveMatchingWords(String keyword) {
		try {
			logger.info("Making request for {}", keyword);
			final String json = restTemplate.getForObject(uri, String.class, ALIAS, keyword);
			final Object[] result = mapper.readValue(json, Object[].class);
			return (List<String>) result[1];
		} catch (JsonProcessingException e) {
			logger.error("Exception encountered: ", e);
		}
		return new ArrayList<>();
	}

}
