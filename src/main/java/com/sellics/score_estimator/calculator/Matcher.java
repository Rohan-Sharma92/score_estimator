package com.sellics.score_estimator.calculator;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sellics.score_estimator.core.IMatchingWordRetriever;

@Component
public class Matcher implements IMatcher {

	private static final int NO_MATCH_FACTOR = 1;
	private final IMatchingWordRetriever matchingWordRetriever;
	private static final int COMPLETE_MATCH_FACTOR = 3;
	private static final int PARTIAL_MATCH_FACTOR = 2;
	private final Logger logger;

	@Autowired
	public Matcher(final IMatchingWordRetriever matchingWordRetriever, final Logger logger) {
		this.matchingWordRetriever = matchingWordRetriever;
		this.logger = logger;
	}

	@Override
	public MatchResult getMatchResult(String keyword, String searchValue) {
		List<String> matchingWords = matchingWordRetriever.retrieveMatchingWords(searchValue);
		logger.debug("Received top search results. Keyword {}, SearchValue: {} ,Words: {}",keyword,searchValue,matchingWords);
		return evaluateMatchResult(keyword, matchingWords);
	}

	private MatchResult evaluateMatchResult(String keyword, List<String> matchingWords) {
		boolean isCompleteMatch = matchingWords.stream().anyMatch(word -> word.equalsIgnoreCase(keyword));
		if (isCompleteMatch)
			return new MatchResult(true, COMPLETE_MATCH_FACTOR);
		boolean isPrefixMatch = matchingWords.stream().allMatch(word -> {
			String upperCaseWord = word.toUpperCase();
			String keyUpper = keyword.toUpperCase();
			return upperCaseWord.startsWith(keyUpper);
		});
		if (isPrefixMatch)
			return new MatchResult(true, PARTIAL_MATCH_FACTOR);
		return new MatchResult(false, NO_MATCH_FACTOR);

	}

}
