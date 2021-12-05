package com.sellics.score_estimator.dto;

import com.sellics.score_estimator.model.Score;

public class ScoreDTO {

	private static final double MAX_POINTS = 100d;
	private final String keyword;

	private Double score;
	
	private final double pointsPerCharacter;
	
	public ScoreDTO(final String keyword) {
		this.keyword = keyword;
		this.score=MAX_POINTS;
		this.pointsPerCharacter=getPointsPerCharacter(keyword);
	}

	public String getKeyword() {
		return keyword;
	}

	public void awardPoints(Integer multiplier) {
		this.score += calculatePoints(pointsPerCharacter, multiplier);
		if (this.score > MAX_POINTS)
			this.score = MAX_POINTS;
	}

	public void givePenalty(Integer multiplier) {
		this.score -= calculatePoints(pointsPerCharacter, multiplier);
		if (this.score < 0)
			this.score = 0d;
	}

	public Score processFinalScore() {
		return new Score(keyword, score.intValue());
	}

	private double getPointsPerCharacter(String keyword) {
		return MAX_POINTS / (keyword.length() - 1);
	}
	
	private double calculatePoints(double pointsPerChar, Integer multiplier) {
		return pointsPerChar * multiplier;
	}
}
