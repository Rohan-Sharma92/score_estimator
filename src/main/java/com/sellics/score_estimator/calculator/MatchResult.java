package com.sellics.score_estimator.calculator;

public class MatchResult {

	private final Boolean isMatch;
	private final Integer multiplier;

	public MatchResult(final Boolean isMatch, Integer multiplier) {
		this.isMatch = isMatch;
		this.multiplier = multiplier;
	}

	public Boolean isMatch() {
		return isMatch;
	}

	public Integer getMatchFactor() {
		return multiplier;
	}
}
