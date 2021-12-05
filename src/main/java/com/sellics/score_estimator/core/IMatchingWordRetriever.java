package com.sellics.score_estimator.core;

import java.util.List;

public interface IMatchingWordRetriever {

	public List<String> retrieveMatchingWords(String keyword);
}
