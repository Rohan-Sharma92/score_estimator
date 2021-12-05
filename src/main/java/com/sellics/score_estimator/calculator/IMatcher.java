package com.sellics.score_estimator.calculator;

public interface IMatcher {

	MatchResult getMatchResult(String keyword, String searchValue);
}
