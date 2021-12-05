package com.sellics.score_estimator.factory;

import com.sellics.score_estimator.dto.ScoreDTO;

public interface IScoreDTOFactory {

	public ScoreDTO create(String keyword);
}
