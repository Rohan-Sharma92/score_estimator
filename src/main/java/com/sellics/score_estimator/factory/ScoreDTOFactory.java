package com.sellics.score_estimator.factory;

import org.springframework.stereotype.Component;

import com.sellics.score_estimator.dto.ScoreDTO;

@Component
public class ScoreDTOFactory implements IScoreDTOFactory {

	@Override
	public ScoreDTO create(String keyword) {
		return new ScoreDTO(keyword);
	}

}
