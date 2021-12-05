package com.sellics.score_estimator.service;

import com.sellics.score_estimator.model.Score;

public interface IScoreEstimationService {

	public Score calculateScore(String keyword);
}
