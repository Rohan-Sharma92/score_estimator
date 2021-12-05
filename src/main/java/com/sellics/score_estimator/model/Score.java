package com.sellics.score_estimator.model;

public class Score {

	private String keyword;

	private Integer score;

	public Score(final String keyword, final Integer score) {
		this.keyword = keyword;
		this.score = score;
	}

	public String getKeyword() {
		return keyword;
	}

	public Integer getScore() {
		return score;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("Keyword: ").append(this.keyword).append(", Score: ").append(this.score)
				.toString();
	}
}
